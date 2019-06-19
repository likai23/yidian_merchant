/**
 * @filename:SupplierPayAddcount 2019-06-19 03:43:18
 * @project ydsh-saas-service-goods  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * <p>说明： 供应商充值记录表实体类</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SupplierPayAddcount implements Serializable {

	private static final long serialVersionUID = 1560930198021L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "sdId" , value = "供应商基本资料表的id")
	private Long sdId;
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
	@ApiModelProperty(name = "createId" , value = "提交人/创建人")
	private String createId;
	@ApiModelProperty(name = "reviewId" , value = "审核人")
	private String reviewId;
	@ApiModelProperty(name = "reviewStatus" , value = "审批状态：0-未审核，1-审核通过，2-审核不通过")
	private String reviewStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "reviewTime" , value = "修改时间（自动生成, MySQL 5.7+）")
	private Date reviewTime;
	@ApiModelProperty(name = "reviewRemarks" , value = "备注")
	private String reviewRemarks;
	@ApiModelProperty(name = "updateId" , value = "修改人ID")
	private Long updateId;
	@ApiModelProperty(name = "STATUS" , value = "数据状态（1：正常[√]；0：删除）")
	private Integer STATUS;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "提交时间")
	private Date createTime;
}
