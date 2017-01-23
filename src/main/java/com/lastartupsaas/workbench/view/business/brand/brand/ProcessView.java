package com.lastartupsaas.workbench.view.business.brand.brand;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lastartupsaas.workbench.domain.KeyValueObject;
import com.lastartupsaas.workbench.view.BaseWorkBenchViewView;
import com.lastartupsaas.workbench.view.ViewContext;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.CheckboxMultilevelGroupEditor;
import com.lastartupsaas.workbench.view.form.impl.LabelFieldEditor;
import com.vaadin.spring.annotation.SpringView;

/**
 * 品牌 > 品牌 > 品牌查看页面 > 基本信息
 * 
 * @author lifeilong
 * @date: 2016-12-13
 */
@SpringView(name = ProcessView.VIEW_NAME)
public class ProcessView extends BaseWorkBenchViewView {

	private static final long serialVersionUID = -3671736928428945007L;
	public static final String VIEW_NAME = "process.view";

	@Override
	protected String getObjectName(Object obj) {
		return "";
	}

	@Override
	protected void declareFormAgent(FormAgent formAgent) {
		List<FormField> intention_gold = new ArrayList<FormField>();
		intention_gold.add(new FormField("意向金范围", "intention_gold", new LabelFieldEditor("1,000.00", "100%"), false, null, true));
		formAgent.addFieldListToMap("意向金", intention_gold);

		List<FormField> joining_condition = new ArrayList<FormField>();
		List<KeyValueObject> conditions = new ArrayList<>();
		conditions.add(new KeyValueObject("1", "要求加盟者具有相关的工作经验"));
		conditions.add(new KeyValueObject("2", "百分百投入本品牌的经营"));
		conditions.add(new KeyValueObject("3", "加盟者居住在申请加盟区域，且已在该区域居住至少一年"));
		conditions.add(new KeyValueObject("4", "愿意主动配合及时参加总部各项培训及促销活动"));
		conditions.add(new KeyValueObject("5", "店面根据公司要求进行装修"));
		conditions.add(new KeyValueObject("6", "其他"));
		joining_condition.add(new FormField("加盟条件", "joining_condition", new CheckboxMultilevelGroupEditor(conditions, "key", "value", "vertical"),
				false, null, true));
		formAgent.addFieldListToMap("设置加盟条件", joining_condition);

		List<FormField> process_first_stage = new ArrayList<FormField>();
		List<KeyValueObject> first_stage = new ArrayList<>();
		first_stage.add(new KeyValueObject("1", "加盟者现场考察"));
		first_stage.add(new KeyValueObject("2", "公司对加盟者投资评估分析"));
		first_stage.add(new KeyValueObject("3", "加盟洽谈后签订加盟合同"));
		first_stage.add(new KeyValueObject("4", "证照办理指导"));
		first_stage.add(new KeyValueObject("5", "其他"));
		process_first_stage.add(new FormField("第一阶段", "first_stage", new CheckboxMultilevelGroupEditor(first_stage, "key", "value", "vertical"), false,
				null, true));
		formAgent.addFieldListToMap("加盟流程第一阶段", process_first_stage);

		List<FormField> process_second_stage = new ArrayList<FormField>();
		List<KeyValueObject> second_stage = new ArrayList<>();
		second_stage.add(new KeyValueObject("1", "加盟商培训"));
		second_stage.add(new KeyValueObject("2", "物料配送、设备安装及调式指导"));
		second_stage.add(new KeyValueObject("3", "开业策划筹备指导"));
		second_stage.add(new KeyValueObject("5", "其他"));
		process_second_stage.add(new FormField("第二阶段", "second_stage", new CheckboxMultilevelGroupEditor(second_stage, "key", "value", "vertical"),
				false, null, true));
		formAgent.addFieldListToMap("加盟流程第二阶段", process_second_stage);

		List<FormField> process_third_stage = new ArrayList<FormField>();
		List<KeyValueObject> third_stage = new ArrayList<>();
		third_stage.add(new KeyValueObject("1", "正式开业后期服务"));
		third_stage.add(new KeyValueObject("5", "其他"));
		process_third_stage.add(new FormField("第三阶段", "third_stage", new CheckboxMultilevelGroupEditor(third_stage, "key", "value", "vertical"), false,
				null, true));
		formAgent.addFieldListToMap("加盟流程第三阶段", process_third_stage);
	}

	@Override
	protected Object loadFormDataByContext(ViewContext vc) {
		String id = vc.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
		}
		return null;
	}

	@Override
	protected String getReturnViewUrl() {
		return null;
	}
}
