/**
 * @filename:SupplierGoods 2019-06-11 09:49:43
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;

/**   
 * <p>代码自动生成，请勿修改</p>
 * 
 * <p>说明： 供应商和商品的关系实体类</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SupplierGoods implements Serializable {

	private static final long serialVersionUID = 1560217783249L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "sdId" , value = "供应商基本资料表的id")
	private String sdId;
	@ApiModelProperty(name = "gsId" , value = "sku表id")
	private Long gsId;
	@ApiModelProperty(name = "goodsType" , value = "商品类型：1.卡券商品，2.充值商品")
	private String goodsType;
	@ApiModelProperty(name = "ifDelay" , value = "是否能延期")
	private String ifDelay;
	@ApiModelProperty(name = "purchaseTime" , value = "采购时长")
	private Integer purchaseTime;
	@ApiModelProperty(name = "supplyPrice" , value = "供应价/采购价")
	private Long supplyPrice;
	@ApiModelProperty(name = "taxRate" , value = "税率")
	private Integer taxRate;
	@ApiModelProperty(name = "invoiceType" , value = "发票类型:1.普通发票，2.专用发票，3.无")
	private String invoiceType;
	@ApiModelProperty(name = "payMethod" , value = "结算方式：1.入库结算，2.核销结算，3.发放结算")
	private String payMethod;
	@ApiModelProperty(name = "shopmessType" , value = "门店信息类型")
	private String shopmessType;
	@ApiModelProperty(name = "shopMessage" , value = "门店信息")
	private String shopMessage;
	@ApiModelProperty(name = "status" , value = "状态：1-启用，2-禁用")
	private String status;
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
	@ApiModelProperty(name = "createId" , value = "创建人ID")
	private Long createId;
	@ApiModelProperty(name = "updateId" , value = "修改人ID")
	private Long updateId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "创建时间（自动生成, MySQL 5.7+）")
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "修改时间（自动生成, MySQL 5.7+）")
	private Date updateTime;
}
