/**
 * @filename:SupplyPriceAdjustmentDetail 2019-06-19 03:43:18
 * @project ydsh-saas-service-goods  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**   
 * <p>代码自动生成，请勿修改</p>
 * 
 * <p>说明： 供应价调整子表（明细）实体类</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SupplyPriceAdjustmentDetail implements Serializable {

	private static final long serialVersionUID = 1560930198219L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "bppaId" , value = "供应价调整主表id")
	private Long bppaId;
	@ApiModelProperty(name = "gcsId" , value = "sku表id")
	private Long gcsId;
	@ApiModelProperty(name = "gcsNo" , value = "商品编号")
	private String gcsNo;
	@ApiModelProperty(name = "gcsSku" , value = "sku")
	private String gcsSku;
	@ApiModelProperty(name = "gcsName" , value = "商品名称")
	private String gcsName;
	@ApiModelProperty(name = "gcsDenomination" , value = "面值")
	private Long gcsDenomination;
	@ApiModelProperty(name = "gcsAttribute" , value = "商品属性")
	private String gcsAttribute;
	@ApiModelProperty(name = "gcsOldPurchasePrice" , value = "当前供应价")
	private Long gcsOldPurchasePrice;
	@ApiModelProperty(name = "gcsNewPurchasePrice" , value = "调整供应价")
	private Long gcsNewPurchasePrice;
}
