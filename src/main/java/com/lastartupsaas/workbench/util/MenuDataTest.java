package com.lastartupsaas.workbench.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

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
		Resource order_manage = new Resource(2001L, "交易订单", false, false, null, null, null);
		trade_manage.appenMenuItem(order_manage);
		order_manage.appenMenuItem(new Resource(200101L, "交易管理", false, false, null, null, "order_list.view"));
		order_manage.appenMenuItem(new Resource(200102L, "投诉管理", false, false, null, null, "complain_list.view"));
		Resource balance_manage = new Resource(2002L, "结算管理", false, false, null, null, null);
		trade_manage.appenMenuItem(balance_manage);
		balance_manage.appenMenuItem(new Resource(200201L, "结算单管理", false, false, null, null, "balance_list.view"));
		balance_manage.appenMenuItem(new Resource(200202L, "未出账交易单", false, false, null, null, "not_out_account_list.view"));
		menuList.add(trade_manage);

		Resource community_manage = new Resource(3000L, "社区运营", false, false, null, null, null);
		Resource topic_manage = new Resource(3001L, "话题", false, false, null, null, null);
		community_manage.appenMenuItem(topic_manage);
		topic_manage.appenMenuItem(new Resource(300101L, "话题列表", false, false, null, null, "topic_list.view"));
		topic_manage.appenMenuItem(new Resource(300102L, "话题推荐", false, false, null, null, "recommend_topic_list.view"));
		Resource dynamic_manage = new Resource(3002L, "动态", false, false, null, null, null);
		community_manage.appenMenuItem(dynamic_manage);
		dynamic_manage.appenMenuItem(new Resource(300201L, "动态管理", false, false, null, null, "dynamic_list.view"));
		Resource comment_manage = new Resource(3003L, "评论", false, false, null, null, null);
		community_manage.appenMenuItem(comment_manage);
		comment_manage.appenMenuItem(new Resource(300301L, "评论管理", false, false, null, null, "comment_list.view"));
		Resource setup_manage = new Resource(3004L, "设置", false, false, null, null, null);
		community_manage.appenMenuItem(setup_manage);
		setup_manage.appenMenuItem(new Resource(300401L, "关键词屏蔽", false, false, null, null, "keyword_list.view"));
		menuList.add(community_manage);

		Resource marketing_manage = new Resource(4000L, "营销", false, false, null, null, null);
		Resource college_manage = new Resource(4001L, "创业学院", false, false, null, null, null);
		marketing_manage.appenMenuItem(college_manage);
		college_manage.appenMenuItem(new Resource(400101L, "分类管理", false, false, null, null, "classify_list.view"));
		college_manage.appenMenuItem(new Resource(400102L, "文章管理", false, false, null, null, "article_list.view"));
		Resource assistance_manage = new Resource(4002L, "创业助力", false, false, null, null, null);
		marketing_manage.appenMenuItem(assistance_manage);
		assistance_manage.appenMenuItem(new Resource(400201L, "助力管理", false, false, null, null, "assistance_list.view"));
		assistance_manage.appenMenuItem(new Resource(400202L, "助理金查看", false, false, null, null, "receive_list.view"));
		Resource consult_manage = new Resource(4003L, "留言咨询", false, false, null, null, null);
		marketing_manage.appenMenuItem(consult_manage);
		consult_manage.appenMenuItem(new Resource(400301L, "留言管理", false, false, null, null, "leave_message_list.view"));
		Resource message_push_manage = new Resource(4004L, "消息推送", false, false, null, null, null);
		marketing_manage.appenMenuItem(message_push_manage);
		message_push_manage.appenMenuItem(new Resource(400401L, "push管理", false, false, null, null, "message_push_list.view"));
		message_push_manage.appenMenuItem(new Resource(400402L, "消息用户管理", false, false, null, null, "message_user_list.view"));
		menuList.add(marketing_manage);

		Resource vip_manage = new Resource(5000L, "会员", false, false, null, null, null);
		Resource vip_list = new Resource(5001L, "会员管理", false, false, null, null, null);
		vip_manage.appenMenuItem(vip_list);
		vip_list.appenMenuItem(new Resource(500101L, "会员列表", false, false, null, null, "member_list.view"));
		vip_list.appenMenuItem(new Resource(500102L, "提现管理", false, false, null, null, "member_list.view"));
		Resource potential_report = new Resource(5002L, "潜能报告", false, false, null, null, null);
		vip_manage.appenMenuItem(potential_report);
		potential_report.appenMenuItem(new Resource(500201L, "潜能报告", false, false, null, null, "role_list.view"));
		menuList.add(vip_manage);

		Resource task_manage = new Resource(7000L, "待办任务", false, false, null, null, null);
		task_manage.appenMenuItem(new Resource(7001L, "待办任务", false, false, null, null, null));
		task_manage.appenMenuItem(new Resource(700101L, "我的任务", false, false, null, null, "my_task_list.view"));
		task_manage.appenMenuItem(new Resource(700102L, "绩效统计", false, false, null, null, "performance_list.view"));
		task_manage.appenMenuItem(new Resource(700103L, "任务库", false, false, null, null, "task_library_list.view"));
		menuList.add(task_manage);

		Resource system_manage = new Resource(6000L, "系统管理", false, false, null, null, null);
		Resource authority_manage = new Resource(6001L, "权限管理", false, false, null, null, null);
		system_manage.appenMenuItem(authority_manage);
		authority_manage.appenMenuItem(new Resource(600101L, "管理员", false, false, null, null, "user_list.view"));
		authority_manage.appenMenuItem(new Resource(600102L, "岗位", false, false, null, null, "post_list.view"));
		authority_manage.appenMenuItem(new Resource(600103L, "角色", false, false, null, null, "role_list.view"));
		authority_manage.appenMenuItem(new Resource(600104L, "资源", false, false, null, null, "resource_list.view"));
		Resource kpi_manage = new Resource(6002L, "KIP管理", false, false, null, null, null);
		system_manage.appenMenuItem(kpi_manage);
		kpi_manage.appenMenuItem(new Resource(600201L, "KIP数据项", false, false, null, null, "kpi_config_list.view"));
		kpi_manage.appenMenuItem(new Resource(600202L, "KPI指标配置", false, false, null, null, "kpi_target_list.view"));
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