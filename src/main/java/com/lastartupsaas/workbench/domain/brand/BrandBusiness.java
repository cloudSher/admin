package com.lastartupsaas.workbench.domain.brand;

import com.lastartupsaas.workbench.domain.BaseObject;

/**
 * Author: lifeilong 
 * Date: 2016-12-09
 */
public class BrandBusiness extends BaseObject {
	private Long id;// 品牌商编号
	private String enterpriseName;// 企业名称
	private String businessLicenseNo;// 工商执照注册号
	private String enterpriseAddress;// 企业地址
	private String contact;// 联系人
	private String contactPhone;// 联系人手机号
	private String reviewState;// 审核状态
	private String smsFlag;// 已发送开户短信
	private String lastOperateTime;// 最后操作时间

	public BrandBusiness() {
	}

	public BrandBusiness(Long id, String enterpriseName, String businessLicenseNo, String enterpriseAddress, String contact, String contactPhone,
			String reviewState, String smsFlag, String lastOperateTime) {
		super();
		this.id = id;
		this.enterpriseName = enterpriseName;
		this.businessLicenseNo = businessLicenseNo;
		this.enterpriseAddress = enterpriseAddress;
		this.contact = contact;
		this.contactPhone = contactPhone;
		this.reviewState = reviewState;
		this.smsFlag = smsFlag;
		this.lastOperateTime = lastOperateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}

	public String getEnterpriseAddress() {
		return enterpriseAddress;
	}

	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getReviewState() {
		return reviewState;
	}

	public void setReviewState(String reviewState) {
		this.reviewState = reviewState;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public String getLastOperateTime() {
		return lastOperateTime;
	}

	public void setLastOperateTime(String lastOperateTime) {
		this.lastOperateTime = lastOperateTime;
	}

}
