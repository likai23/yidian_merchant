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
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class CustomerPayReduceacountQueryDto implements Serializable {

	private static final long serialVersionUID = 1560916528851L;
	

	@ApiModelProperty(name = "sdId" , value = "供应商基本资料表的id")
	private Long sdId;
	@ApiModelProperty(name = "tradeNum" , value = "交易单号")
	private String tradeNum;
	@ApiModelProperty(name = "reviewStatus" , value = "审批状态：0-未审核，1-审核通过，2-审核不通过")
	private String reviewStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "creatimeBegin" , value = "开始时间")
	private Date creatimeBegin;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "creatimeEnd" , value = "结束时间")
	private Date creatimeEnd;
	@ApiModelProperty(name = "customerName" , value = "客户名称")
	private String customerName;
	@ApiModelProperty(name = "cdNo" , value = "客户编码")
	private String cdNo;
	@ApiModelProperty(name = "saleMain" , value = "销售主体")
	private String saleMain;
}
