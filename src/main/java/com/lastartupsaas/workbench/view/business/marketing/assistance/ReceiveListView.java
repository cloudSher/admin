package com.lastartupsaas.workbench.view.business.marketing.assistance;

import java.util.ArrayList;
import java.util.List;

import com.lastartupsaas.workbench.domain.KeyValueObject;
import com.lastartupsaas.workbench.view.BaseWorkBenchListWithSearchView;
import com.lastartupsaas.workbench.view.datagrid.ActionCommand;
import com.lastartupsaas.workbench.view.datagrid.DataGridColumn;
import com.lastartupsaas.workbench.view.datagrid.DataGridModel;
import com.lastartupsaas.workbench.view.datagrid.DataGridRow;
import com.lastartupsaas.workbench.view.datagrid.DataListRequest;
import com.lastartupsaas.workbench.view.form.FormAgent;
import com.lastartupsaas.workbench.view.form.FormBuildLayout;
import com.lastartupsaas.workbench.view.form.FormDataHelper;
import com.lastartupsaas.workbench.view.form.FormField;
import com.lastartupsaas.workbench.view.form.impl.DateFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.InputFieldEditor;
import com.lastartupsaas.workbench.view.form.impl.SelectFieldEditor;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

/**
 * 助理金查看列表页
 * 
 * @author lifeilong
 * @date 2016-12-26
 */
@SpringView(name = ReceiveListView.VIEW_NAME)
public class ReceiveListView extends BaseWorkBenchListWithSearchView {

	private static final long serialVersionUID = -1216252899975275359L;
	public static final String VIEW_NAME = "receive_list.view";

	private FormAgent searchAgent;

	private String searchName;

	public ReceiveListView() {
		this("当前位置：社区运营 > 创业助力 > 助理金查看");
	}

	public ReceiveListView(String caption) {
		this.setViewCaption(caption);
		this.withFilterSection = true;
	}

	@Override
	public void performAction(ActionCommand command, Object... parameters) {
	}

	@Override
	protected void createFilterForm(HorizontalLayout layout) {
		searchAgent = new FormAgent();
		searchAgent.setDataHelper(new FormDataHelper());
		searchAgent.setSearchMode(true);
		searchAgent.setFieldColumnCount(4);
		searchAgent.setCaptionAlignment(Alignment.MIDDLE_LEFT);
		searchAgent.addField(new FormField("领取时间", "start_time", DateFieldEditor.class, false, null, false).setInputDescr("开始领取时间"));
		searchAgent.addField(new FormField("~", "end_time", DateFieldEditor.class, false, null, false).setInputDescr("结束领取时间"));
		List<KeyValueObject> list = new ArrayList<>();
		list.add(new KeyValueObject("1", "会员ID"));
		list.add(new KeyValueObject("2", "手机号"));
		searchAgent.addField(new FormField("", "search_model", new SelectFieldEditor(list, "key", "value", "1", "100%"), false, null, false));
		searchAgent.addField(new FormField("", "search_value", InputFieldEditor.class, false, null, false));

		FormBuildLayout form = searchAgent.buildSearchForm();
		form.setWidth("100%");
		form.setSpacing(true);

		layout.addComponent(form);
		layout.setExpandRatio(form, 1);
	}

	@Override
	protected void doSearchCancelAction() {
		this.searchAgent.resetFormData();
		this.doSearchAction();
	}

	@Override
	protected void doSearchAction() {
		this.searchName = (String) this.searchAgent.getFieldValue("name");
		this.dataGrid.reloadDatas();
	}

	@Override
	public DataGridRow convertRowData(Object item) {

		return new DataGridRow("00000001",
				new Object[] { "00000001", "鸟叔", "2424243", "13222222222", "200", "2018-03-03 10:22", "2018-03-03 10:22", "美食", "黄焖鸡米饭", "已使用" });
	}

	@Override
	public int getDataCount() {
		return 1;
	}

	@Override
	public List<?> getDataList(DataListRequest request) {
		List<String> list = new ArrayList<>();
		list.add("1");
		return list;
	}

	@Override
	protected void setupGridModel(DataGridModel gridModel) {

		gridModel.addColumn(new DataGridColumn("助力金编号", String.class));
		gridModel.addColumn(new DataGridColumn("会员昵称", String.class));
		gridModel.addColumn(new DataGridColumn("会员ID", String.class));
		gridModel.addColumn(new DataGridColumn("手机号", String.class));
		gridModel.addColumn(new DataGridColumn("助力金额", String.class));
		gridModel.addColumn(new DataGridColumn("领取时间", String.class));
		gridModel.addColumn(new DataGridColumn("有效期至", String.class));
		gridModel.addColumn(new DataGridColumn("品类", String.class));
		gridModel.addColumn(new DataGridColumn("品牌", String.class));
		gridModel.addColumn(new DataGridColumn("状态", String.class));
	}
}
