/**
 * @filename:CustomerPayAddcount 2019-06-19 03:43:17
 * @project ydsh-saas-service-goods  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.entity.dto;

import java.io.Serializable;
import java.util.Date;

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
 * <p>说明： 客户充值记录表实体类</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CustomerPayAddcountDto implements Serializable {

	private static final long serialVersionUID = 1560930197598L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "cdId" , value = "客户基本资料id")
	private Long cdId;
	@ApiModelProperty(name = "accountNum" , value = "文件编号")
	private String accountNum;
	@ApiModelProperty(name = "tradeNum" , value = "交易单号")
	private String tradeNum;
	@ApiModelProperty(name = "accountBook" , value = "充值账本：1.预付充值，2.返利充值，3.回款充值")
	private String accountBook;
	@ApiModelProperty(name = "rechargeAmount" , value = "充值金额")
	private String rechargeAmount;
	@ApiModelProperty(name = "rechargeWay" , value = "充值方式:1.银行转账，2.支付宝转账，3.微信转账，4.京东在线转账")
	private String rechargeWay;
	@ApiModelProperty(name = "rechargeBz" , value = "充值备注")
	private String rechargeBz;
	@ApiModelProperty(name = "accountFile" , value = "上传文件")
	private String accountFile;
	@ApiModelProperty(name = "reviewId" , value = "审核人id")
	private Long reviewId;
	@ApiModelProperty(name = "reviewStatus" , value = "是否审核通过：1.是，2.否")
	private String reviewStatus;
	@ApiModelProperty(name = "reviewRemarks" , value = "审核意见")
	private String reviewRemarks;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "reviewTime" , value = "审核时间")
	private Date reviewTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "moneyOntime" , value = "到款时间")
	private Date moneyOntime;
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
	@ApiModelProperty(name = "createId" , value = "创建人ID")
	private Long createId;
	@ApiModelProperty(name = "updateId" , value = "修改人ID")
	private Long updateId;
	@ApiModelProperty(name = "status" , value = "数据状态（1：正常[√]；0：删除）")
	private Integer status;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "创建时间（自动生成, MySQL 5.7+）")
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "修改时间（自动生成, MySQL 5.7+）")
	private Date updateTime;
}
