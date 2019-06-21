/**
 					* @filename:SupplierPayAddcount 2019-06-19 11:55:28
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.entity.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**   
 * <p>代码自动生成，请勿修改</p>
 * 
 * <p>说明： 供应商充值记录表实体类</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierPayReducecountAndDataDto implements Serializable {

	private static final long serialVersionUID = 1560916528851L;
	
	
	@ApiModelProperty(name = "sdId" , value = "供应商基本资料表的id")
	private Long sdId;
	@ApiModelProperty(name = "supplierName" , value = "供应商名称")
	private String supplierName;
	@ApiModelProperty(name = "sdNo" , value = "供应商编号")
	private String sdNo;
	@ApiModelProperty(name = "trade" , value = "行业类别:(数据字典)")
	private String trade;
	@ApiModelProperty(name = "type" , value = "供应商类型:(数据字典)")
	private String type;
	@ApiModelProperty(name = "legperson" , value = "法人")
	private String legperson;
	@ApiModelProperty(name = "reqperson" , value = "负责人")
	private String reqperson;
	@ApiModelProperty(name = "province" , value = "省份")
	private String province;
	@ApiModelProperty(name = "city" , value = "市")
	private String city;
	@ApiModelProperty(name = "reqPhone" , value = "负责人联系方式")
	private String reqPhone;
	@ApiModelProperty(name = "forshort" , value = "供应商简称")
	private String forshort;
	@ApiModelProperty(name = "address" , value = "供应商地址")
	private String address;
	@ApiModelProperty(name = "logo" , value = "供应商logo")
	private String logo;
	@ApiModelProperty(name = "chainIf" , value = "是否连锁:1.是、2.否")
	private String chainIf;
	@ApiModelProperty(name = "bz" , value = "备注")
	private String bz;
	@ApiModelProperty(name = "supplierStatus" , value = "供应商状态")
	private String supplierStatus;
	@ApiModelProperty(name = "registerNum" , value = "工商注册号")
	private String registerNum;
	@ApiModelProperty(name = "legpersonType" , value = "法人证件类型:1.身份证，2.军官证，3.护照")
	private String legpersonType;
	@ApiModelProperty(name = "legpersonId" , value = "法人证件号码")
	private String legpersonId;
	@ApiModelProperty(name = "businessFile" , value = "营业执照副本")
	private String businessFile;
	@ApiModelProperty(name = "registerFile" , value = "注册商标")
	private String registerFile;
	@ApiModelProperty(name = "certificate" , value = "授权书")
	private String certificate;
	@ApiModelProperty(name = "sd_review_id" , value = "供应商基本资料审核人id")
	private Long sd_review_id;
	@ApiModelProperty(name = "sd_review_status" , value = "供应商基本资料审批状态：0-未审核，1-审核通过，2-审核不通过")
	private String sd_review_status;
	@ApiModelProperty(name = "sd_review_remarks" , value = "供应商基本资料审批备注")
	private String sd_review_remarks;
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
	@ApiModelProperty(name = "bsprId" , value = "退款记录主键ID")
	private Long bsprId;
	@ApiModelProperty(name = "refundAmount" , value = "退款金额")
	private Long refundAmount;
	@ApiModelProperty(name = "refundWay" , value = "退款方式:1.银行转账，2.支付宝转账，3.微信转账，4.京东在线转账")
	private String refundWay;
	@ApiModelProperty(name = "fileId" , value = "文件编号")
	private String fileId;
	@ApiModelProperty(name = "tradeNum" , value = "交易编号")
	private String tradeNum;
	@ApiModelProperty(name = "tradeType" , value = "交易账本")
	private String tradeType;
	@ApiModelProperty(name = "file" , value = "上传文件")
	private String file;
	@ApiModelProperty(name = "spr_create_id" , value = "退款记录提交人")
	private String spr_create_id;
	@ApiModelProperty(name = "spr_review_id" , value = "退款记录审核人")
	private String spr_review_id;
	@ApiModelProperty(name = "spr_review_status" , value = "退款记录审批状态：0-未审核，1-审核通过，2-审核不通过")
	private String spr_review_status;
	@ApiModelProperty(name = "reviewRemarks" , value = "审批备注")
	private String reviewRemarks;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "spr_review_time" , value = "退款记录审核时间")
	private Date spr_review_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "spr_create_time" , value = "退款记录提交时间")
	private Date spr_create_time;
	@ApiModelProperty(name = "rechargeall" , value = "退款记录退款总额")
	private Long rechargeall;
}
