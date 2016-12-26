package com.lastartupsaas.workbench.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.lastartupsaas.workbench.domain.admin.Resource;

/**
 * 菜单栏数据管理
 * 
 * @author lifeilong
 */
public class MenuDataTest {

	private static MenuDataTest INSTANCE;

	public synchronized static MenuDataTest getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MenuDataTest();
		}
		return INSTANCE;
	}

	private MenuDataTest() {
	}

	/**
	 * 获取菜单数据
	 */
	public List<Resource> getMenuData() {
		List<Resource> menuList = new ArrayList<Resource>();

		Resource brand_manage = new Resource(1000L, "品牌", false, false, null, null, null);
		Resource brand_business = new Resource(1001L, "品牌商", false, false, null, null, null);
		brand_manage.appenMenuItem(brand_business);
		brand_business.appenMenuItem(new Resource(100101L, "品牌商列表", false, false, null, null, "brand_business_list.view"));
		brand_business.appenMenuItem(new Resource(100102L, "服务合同", false, false, null, null, "service_contract_list.view"));
		brand_business.appenMenuItem(new Resource(100103L, "加盟合同", false, false, null, null, "role_list.view"));
		brand_business.appenMenuItem(new Resource(100104L, "品牌商审核", false, false, null, null, "role_list.view"));
		
		Resource brand = new Resource(1002L, "品牌", false, false, null, null, "user_list.view");
		brand_manage.appenMenuItem(brand);
		brand.appenMenuItem(new Resource(100201L, "品牌列表", false, false, null, null, "user_list.view"));
		brand.appenMenuItem(new Resource(100202L, "品牌类型", false, false, null, null, "role_list.view"));
		brand.appenMenuItem(new Resource(100203L, "品牌审核", false, false, null, null, "role_list.view"));
		menuList.add(brand_manage);

		Resource trade_manage = new Resource(2000L, "交易", false, false, null, null, null);
		trade_manage.appenMenuItem(new Resource(2001L, "交易订单", false, false, null, null, "user_list.view"));
		trade_manage.appenMenuItem(new Resource(2002L, "结算管理", false, false, null, null, "role_list.view"));
		menuList.add(trade_manage);

		Resource community_manage = new Resource(3000L, "社区运营", false, false, null, null, null);
		community_manage.appenMenuItem(new Resource(3001L, "话题", false, false, null, null, null));
		community_manage.appenMenuItem(new Resource(300101L, "话题列表", false, false, null, null, "topic_list.view"));
		community_manage.appenMenuItem(new Resource(300102L, "话题推荐", false, false, null, null, "user_list.view"));
		community_manage.appenMenuItem(new Resource(3002L, "动态", false, false, null, null, null));
		community_manage.appenMenuItem(new Resource(300201L, "动态管理", false, false, null, null, "role_list.view"));
		community_manage.appenMenuItem(new Resource(3003L, "评论", false, false, null, null, null));
		community_manage.appenMenuItem(new Resource(300301L, "评论管理", false, false, null, null, "role_list.view"));
		community_manage.appenMenuItem(new Resource(3004L, "设置", false, false, null, null, null));
		community_manage.appenMenuItem(new Resource(300401L, "关键词屏蔽", false, false, null, null, "role_list.view"));
		menuList.add(community_manage);

		Resource marketing_manage = new Resource(4000L, "营销", false, false, null, null, null);
		marketing_manage.appenMenuItem(new Resource(4001L, "创业学院", false, false, null, null, "user_list.view"));
		marketing_manage.appenMenuItem(new Resource(4002L, "创业助力", false, false, null, null, "role_list.view"));
		marketing_manage.appenMenuItem(new Resource(4003L, "留言咨询", false, false, null, null, "role_list.view"));
		menuList.add(marketing_manage);

		Resource vip_manage = new Resource(5000L, "会员", false, false, null, null, null);
		vip_manage.appenMenuItem(new Resource(5001L, "会员管理", false, false, null, null, "user_list.view"));
		vip_manage.appenMenuItem(new Resource(5002L, "潜能报告", false, false, null, null, "role_list.view"));
		menuList.add(vip_manage);

		Resource system_manage = new Resource(6000L, "系统管理", false, false, null, null, null);
		Resource authority_manage = new Resource(6001L, "权限管理", false, false, null, null, null);
		system_manage.appenMenuItem(authority_manage);
		authority_manage.appenMenuItem(new Resource(600101L, "管理员", false, false, null, null, "user_list.view"));
		authority_manage.appenMenuItem(new Resource(600102L, "权限组", false, false, null, null, "role_list.view"));
		menuList.add(system_manage);

		return menuList;
	}

	/**
	 * 根据编码获取菜单数据
	 */
	public Resource getMenuByCode(String menuCode) {
		if (StringUtils.isNotBlank(menuCode)) {
			List<Resource> resources = getMenuData();
			for (Resource resource : resources) {
				List<Resource> childResources = resource.getResourceList();
				for (Resource resource2 : childResources) {
					// if (menuCode.equals(resource2.getMenuCode())) {
					// return resource2;
					// }
				}
			}
		}
		return null;
	}
}