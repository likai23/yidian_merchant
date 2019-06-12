/**
 * @filename:CustomerData 2019-06-11 09:49:42
 * @project ydsh-saas-service-merchant  V1.0
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
 * <p>说明： 客户基本资料表实体类</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CustomerData implements Serializable {

	private static final long serialVersionUID = 1560217782793L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "cdNo" , value = "客户编号")
	private String cdNo;
	@ApiModelProperty(name = "customerName" , value = "客户姓名")
	private String customerName;
	@ApiModelProperty(name = "customerCategory" , value = "行业类别")
	private String customerCategory;
	@ApiModelProperty(name = "customerType" , value = "客户类型")
	private String customerType;
	@ApiModelProperty(name = "customerPlatformIds" , value = "客户来源")
	private String customerPlatformIds;
	@ApiModelProperty(name = "customerLegperson" , value = "法人")
	private String customerLegperson;
	@ApiModelProperty(name = "customerDockperson" , value = "对接人")
	private String customerDockperson;
	@ApiModelProperty(name = "customerForshort" , value = "客户简称")
	private String customerForshort;
	@ApiModelProperty(name = "customerProvince" , value = "省份")
	private String customerProvince;
	@ApiModelProperty(name = "customerCity" , value = "市")
	private String customerCity;
	@ApiModelProperty(name = "customerDockphone" , value = "对接联系方式")
	private String customerDockphone;
	@ApiModelProperty(name = "customerLogo" , value = "客户logo")
	private String customerLogo;
	@ApiModelProperty(name = "customerAddress" , value = "营业地址")
	private String customerAddress;
	@ApiModelProperty(name = "chainIf" , value = "是否连锁:1.是、2.否")
	private String chainIf;
	@ApiModelProperty(name = "salesManId" , value = "业务员id")
	private String salesManId;
	@ApiModelProperty(name = "saleMain" , value = "销售主体")
	private String saleMain;
	@ApiModelProperty(name = "phonechangeIf" , value = "是否开启话费兌换码：1.是，2.否")
	private String phonechangeIf;
	@ApiModelProperty(name = "registerNumber" , value = "工商注册号")
	private String registerNumber;
	@ApiModelProperty(name = "customerPersonType" , value = "法人证件类型:1.身份证，2.军官证，3.护照")
	private String customerPersonType;
	@ApiModelProperty(name = "cuspersonId" , value = "法人身份证")
	private String cuspersonId;
	@ApiModelProperty(name = "businessFile" , value = "营业执照副本")
	private String businessFile;
	@ApiModelProperty(name = "idcardOn" , value = "身份证正面")
	private String idcardOn;
	@ApiModelProperty(name = "idcardUnder" , value = "身份证反面")
	private String idcardUnder;
	@ApiModelProperty(name = "customernsNum" , value = "纳税人识别号（开票信息）")
	private String customernsNum;
	@ApiModelProperty(name = "customernsAddress" , value = "地址（开票信息）")
	private String customernsAddress;
	@ApiModelProperty(name = "customerPhone" , value = "联系电话（开票信息）")
	private String customerPhone;
	@ApiModelProperty(name = "bankOpen" , value = "开户行（开票信息）")
	private String bankOpen;
	@ApiModelProperty(name = "bankId" , value = "银行账号（开票信息）")
	private Long bankId;
	@ApiModelProperty(name = "email" , value = "邮箱（开票信息）")
	private String email;
	@ApiModelProperty(name = "h5Address" , value = "h5商城地址")
	private String h5Address;
	@ApiModelProperty(name = "channelNum" , value = "渠道号")
	private String channelNum;
	@ApiModelProperty(name = "openDid" , value = "")
	private String openDid;
	@ApiModelProperty(name = "md5Password" , value = "md5秘钥")
	private String md5Password;
	@ApiModelProperty(name = "desPassword" , value = "")
	private String desPassword;
	@ApiModelProperty(name = "platformUseAmount" , value = "平台使用费")
	private Long platformUseAmount;
	@ApiModelProperty(name = "platformUse" , value = "平台使用率")
	private Integer platformUse;
	@ApiModelProperty(name = "reviewStatus" , value = "是否审核通过：0.待审核，1.审核通过，2.审核未通过")
	private String reviewStatus;
	@ApiModelProperty(name = "reviewId" , value = "审核人id")
	private Long reviewId;
	@ApiModelProperty(name = "reviewRemarks" , value = "审核意见")
	private String reviewRemarks;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "reviewTime" , value = "审核时间")
	private Date reviewTime;
	@ApiModelProperty(name = "submitId" , value = "提交人")
	private Long submitId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "submitTime" , value = "提交时间/创建时间")
	private Date submitTime;
	@ApiModelProperty(name = "customerStatus" , value = "客户状态:1-启用，2-禁用，3-黑名单")
	private String customerStatus;
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
	@ApiModelProperty(name = "updateTime" , value = "修改时间（自动生成, MySQL 5.7+）")
	private Date updateTime;
}
