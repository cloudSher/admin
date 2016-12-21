package com.startup.saas.workbench.domain.brand;

import com.startup.saas.workbench.domain.BaseObject;

/**
 * Author: lifeilong 
 * Date: 2016-12-09
 */
public class ServiceContract extends BaseObject {
	private Long id;// 服务合同编号
	private String contractNo;// 服务合同编号
	private String firstParty;// 甲方
	private String secondParty;// 乙方
	private String signTime;// 签约时间
	private String cooperateEffeDate;// 合作有效期
	private String businessLicenseNo;// 工商执照注册号
	private String taxNo;// 税务登记证号
	private String legalPerson;// 法定代表人
	private String accountName;// 户名
	private String accountNo;// 银行账号
	private String bankName;// 开户行
	private String settlementPeriod;// 结算周期
	private String settlementDimension;// 结算维度
	private String splitFlag;// 是否分账
	private String businessLicenceImg;// 营业执照照片
	private String organizationCodeImg;// 组织机构代码照片
	private String contractImg;// 合同照片
	private String lastOperateTime;// 最后操作时间
	private String operator;// 合同照片

	public ServiceContract() {
	}

	public ServiceContract(Long id, String contractNo, String firstParty, String secondParty, String signTime, String cooperateEffeDate,
			String businessLicenseNo, String taxNo, String legalPerson, String accountName, String accountNo, String bankName,
			String settlementPeriod, String settlementDimension, String splitFlag, String businessLicenceImg, String organizationCodeImg,
			String contractImg, String lastOperateTime, String operator) {
		super();
		this.id = id;
		this.contractNo = contractNo;
		this.firstParty = firstParty;
		this.secondParty = secondParty;
		this.signTime = signTime;
		this.cooperateEffeDate = cooperateEffeDate;
		this.businessLicenseNo = businessLicenseNo;
		this.taxNo = taxNo;
		this.legalPerson = legalPerson;
		this.accountName = accountName;
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.settlementPeriod = settlementPeriod;
		this.settlementDimension = settlementDimension;
		this.splitFlag = splitFlag;
		this.businessLicenceImg = businessLicenceImg;
		this.organizationCodeImg = organizationCodeImg;
		this.contractImg = contractImg;
		this.lastOperateTime = lastOperateTime;
		this.operator = operator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getFirstParty() {
		return firstParty;
	}

	public void setFirstParty(String firstParty) {
		this.firstParty = firstParty;
	}

	public String getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(String secondParty) {
		this.secondParty = secondParty;
	}

	public String getSignTime() {
		return signTime;
	}

	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}

	public String getCooperateEffeDate() {
		return cooperateEffeDate;
	}

	public void setCooperateEffeDate(String cooperateEffeDate) {
		this.cooperateEffeDate = cooperateEffeDate;
	}

	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}

	public String getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSettlementPeriod() {
		return settlementPeriod;
	}

	public void setSettlementPeriod(String settlementPeriod) {
		this.settlementPeriod = settlementPeriod;
	}

	public String getSettlementDimension() {
		return settlementDimension;
	}

	public void setSettlementDimension(String settlementDimension) {
		this.settlementDimension = settlementDimension;
	}

	public String getSplitFlag() {
		return splitFlag;
	}

	public void setSplitFlag(String splitFlag) {
		this.splitFlag = splitFlag;
	}

	public String getBusinessLicenceImg() {
		return businessLicenceImg;
	}

	public void setBusinessLicenceImg(String businessLicenceImg) {
		this.businessLicenceImg = businessLicenceImg;
	}

	public String getOrganizationCodeImg() {
		return organizationCodeImg;
	}

	public void setOrganizationCodeImg(String organizationCodeImg) {
		this.organizationCodeImg = organizationCodeImg;
	}

	public String getContractImg() {
		return contractImg;
	}

	public void setContractImg(String contractImg) {
		this.contractImg = contractImg;
	}

	public String getLastOperateTime() {
		return lastOperateTime;
	}

	public void setLastOperateTime(String lastOperateTime) {
		this.lastOperateTime = lastOperateTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}