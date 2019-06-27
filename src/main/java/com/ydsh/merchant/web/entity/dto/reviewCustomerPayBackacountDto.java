/**
 * @filename:CustomerPayBackacount 2019-06-19 03:43:17
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
 * <p>说明： 客户回款记录表实体类</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class reviewCustomerPayBackacountDto implements Serializable {

	private static final long serialVersionUID = 1560930197689L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "cdId" , value = "客户基本资料id")
	private Long cdId;
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
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
}
