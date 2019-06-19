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
public class SupplierPayAddcountAndDataDto implements Serializable {

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
	@ApiModelProperty(name = "status" , value = "删除标志")
	private String status;
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
	@ApiModelProperty(name = "spaId" , value = "充值表id")
	private Long spaId;
	@ApiModelProperty(name = "accountNum" , value = "文件编号")
	private String accountNum;
	@ApiModelProperty(name = "tradeNum" , value = "交易单号")
	private String tradeNum;
	@ApiModelProperty(name = "accountBook" , value = "充值账本：1.余额充值，2.佣金充值")
	private String accountBook;
	@ApiModelProperty(name = "recharge" , value = "充值金额")
	private Long recharge;
	@ApiModelProperty(name = "rechargeWay" , value = "充值方式:1.银行转账，2.支付宝转账，3.微信转账，4.京东在线转账")
	private String rechargeWay;
	@ApiModelProperty(name = "addFile" , value = "上传文件")
	private String addFile;
	@ApiModelProperty(name = "addBz" , value = "充值备注")
	private String addBz;
	@ApiModelProperty(name = "spa_create_id" , value = "供应商充值记录提交人/创建人")
	private String spa_create_id;
	@ApiModelProperty(name = "spa_review_id" , value = "供应商充值记录审核人")
	private String spa_review_id;
	@ApiModelProperty(name = "spa_review_status" , value = "供应商充值记录审批状态：0-未审核，1-审核通过，2-审核不通过")
	private String spa_review_status;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "spa_review_time" , value = "供应商充值记录审核时间")
	private Date spa_review_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "spa_create_time" , value = "供应商充值记录提交时间")
	private Date spa_create_time;
	@ApiModelProperty(name = "rechargeall" , value = "供应商充值总额")
	private Long rechargeall;
}
