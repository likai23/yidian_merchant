/**
 * @filename:SupplyPriceAdjustment 2019-06-28 03:07:11
 * @project ydsh-saas-service-goods  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.entity.dto;

import java.io.Serializable;

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
public class getSupplyPriceAdjustmentAndDetailsList implements Serializable {

	private static final long serialVersionUID = 1561705631441L;
	
	@ApiModelProperty(name = "id" , value = "调整单主键ID")
	private Long id;
}
