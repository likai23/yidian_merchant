/**
 * @filename:SupplyPriceAdjustment 2019-06-28 02:42:41
 * @project ydsh-saas-service-goods  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**   
 * <p>代码自动生成，请勿修改</p>
 * 
 * <p>说明： 供应价调整单:采购管理-供应价设置实体类</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class saveSupplyPriceAdjustmentAndDetails implements Serializable {

	private static final long serialVersionUID = 1561704161798L;
	
	@TableId(value = "id", type = IdType.AUTO)
//	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "supplyPriceAdjustmentNo" , value = "价格调整单号")
	private String supplyPriceAdjustmentNo;
	@ApiModelProperty(name = "supplierId" , value = "供应商id")
	private Long supplierId;
	@ApiModelProperty(name = "remarks" , value = "调整单备注")
	private String remarks;
	@ApiModelProperty(name = "createId" , value = "提交人")
	private Long createId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "提交时间")
	private Date createTime;
	@ApiModelProperty(name = "reviewId" , value = "处理人/审核人")
	private Long reviewId;
	@ApiModelProperty(name = "reviewStatus" , value = "调整单状态/审批状态")
	private String reviewStatus;
	@ApiModelProperty(name = "reviewRemarks" , value = "审核备注")
	private String reviewRemarks;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "reviewTime" , value = "审核时间")
	private Date reviewTime;
	@ApiModelProperty(name = "supplyPriceAdjustmentDetailList" , value = "供应价调整子表明细list")
	private List<SupplyPriceAdjustmentDetailDto> supplyPriceAdjustmentDetailList;
}
