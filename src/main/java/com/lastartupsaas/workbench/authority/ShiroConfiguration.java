package com.lastartupsaas.workbench.authority;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.lastartupsaas.workbench.domain.admin.Resource;
import com.lastartupsaas.workbench.util.MenuDataTest;

/**
 * Shiro配置,相当于SpringMVC 中的XML配置
 *
 * @author lifeilong
 * @date 2017-01-13
 */
@Configuration
public class ShiroConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

	/**
	 * EhCacheManager，缓存管理
	 * <p/>
	 * 用户登陆成功后，把用户信息和权限信息缓存起来，然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
	 * 
	 * @return
	 */
	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
		return em;
	}

	/**
	 * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
	 * 
	 * @param cacheManager
	 * @return
	 */
	@Bean(name = "myShiroRealm")
	public MyShiroRealm myShiroRealm(EhCacheManager cacheManager) {
		MyShiroRealm realm = new MyShiroRealm();
		realm.setCacheManager(cacheManager);
		return realm;
	}

	/**
	 * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类
	 * <p/>
	 * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
	 * <p/>
	 * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
	 * 
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
	 * 
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	/**
	 * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
	 * 
	 * @param myShiroRealm
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(myShiroRealm);
		// <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		dwsm.setCacheManager(getEhCacheManager());
		return dwsm;
	}

	/**
	 * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类
	 * <p/>
	 * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
	 * <p/>
	 * 老实说，这里注入securityManager，我不知道有啥用，从source上看不出它在什么地方会被调用。
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}

	/**
	 * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
	 * <p/>
	 * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象， 然后读取数据库相关配置，配置到 shiroFilterFactoryBean
	 * 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
	 *
	 * @param myShiroRealm
	 * @param stuService
	 * @param scoreDao
	 * @return
	 * @author SHANHY
	 * @create 2016年1月14日
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new MShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/#!login");
		// 登录成功后要跳转的连接
		shiroFilterFactoryBean.setSuccessUrl("/");
		shiroFilterFactoryBean.setUnauthorizedUrl("/#!error.view");

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// authc：该过滤器下的页面必须验证后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter

		logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
		List<Resource> resources = MenuDataTest.getInstance().getMenuData();
		if (resources != null && resources.size() > 0) {
			setFilterChainDefinitionMap(filterChainDefinitionMap, resources);
		}
		filterChainDefinitionMap.put("/#!login", "anon");// anon可以理解为不拦截
		filterChainDefinitionMap.put("/#!error", "anon");// anon可以理解为不拦截
		filterChainDefinitionMap.put("/**", "anon");// anon 可以理解为不拦截

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	private void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap, List<Resource> resources) {
		for (Resource resource : resources) {
			if (StringUtils.isNotBlank(resource.getActive())) {
				filterChainDefinitionMap.put("/#!" + resource.getActive(), "authc,perms[" + resource.getActive() + "]");
			}
			if (resource.getResourceList() != null && resource.getResourceList().size() > 0) {
				setFilterChainDefinitionMap(filterChainDefinitionMap, resource.getResourceList());
			}
		}
	}
}