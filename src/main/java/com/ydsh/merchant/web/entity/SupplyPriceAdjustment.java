/**
 * @filename:SupplyPriceAdjustment 2019-06-11 09:49:43
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**   
 * <p>代码自动生成，请勿修改</p>
 * 
 * <p>说明： 供应价调整单实体类</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SupplyPriceAdjustment implements Serializable {

	private static final long serialVersionUID = 1560217783334L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "supplyPriceAdjustmentNo" , value = "价格调整单号")
	private String supplyPriceAdjustmentNo;
	@ApiModelProperty(name = "reviewStatus" , value = "调整单状态/审批状态")
	private String reviewStatus;
	@ApiModelProperty(name = "supplierId" , value = "供应商id")
	private Long supplierId;
	@ApiModelProperty(name = "remarks" , value = "调整单备注")
	private String remarks;
	@ApiModelProperty(name = "bppaReviewRemarks" , value = "审核备注")
	private String bppaReviewRemarks;
	@ApiModelProperty(name = "createName" , value = "提交人姓名")
	private String createName;
	@ApiModelProperty(name = "reviewName" , value = "审核人姓名")
	private String reviewName;
	@ApiModelProperty(name = "createId" , value = "提交人")
	private Long createId;
	@ApiModelProperty(name = "reviewId" , value = "处理人/审核人")
	private Long reviewId;
}
