/**
 * @filename:SupplierData 2019-06-11 04:30:28
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
 * <p>说明： 表注释实体类</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SupplierData implements Serializable {

	private static final long serialVersionUID = 1560241828570L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "supplierName" , value = "供应商名称")
	private String supplierName;
	@ApiModelProperty(name = "sdNo" , value = "供应商编号")
	private String sdNo;
	@ApiModelProperty(name = "trade" , value = "行业类别:(数据字典)")
	private String trade;
	@ApiModelProperty(name = "type" , value = "供应商类型:(数据字典)")
	private String type;
	@ApiModelProperty(name = "legperson" , value = "法人")
	private String legperson;
	@ApiModelProperty(name = "reqperson" , value = "负责人")
	private String reqperson;
	@ApiModelProperty(name = "province" , value = "省份")
	private String province;
	@ApiModelProperty(name = "city" , value = "市")
	private String city;
	@ApiModelProperty(name = "reqPhone" , value = "负责人联系方式")
	private String reqPhone;
	@ApiModelProperty(name = "forshort" , value = "供应商简称")
	private String forshort;
	@ApiModelProperty(name = "address" , value = "供应商地址")
	private String address;
	@ApiModelProperty(name = "logo" , value = "供应商logo")
	private String logo;
	@ApiModelProperty(name = "chainIf" , value = "是否连锁:1.是、2.否")
	private String chainIf;
	@ApiModelProperty(name = "bz" , value = "备注")
	private String bz;
	@ApiModelProperty(name = "supplierStatus" , value = "供应商状态")
	private String supplierStatus;
	@ApiModelProperty(name = "registerNum" , value = "工商注册号")
	private String registerNum;
	@ApiModelProperty(name = "legpersonType" , value = "法人证件类型:1.身份证，2.军官证，3.护照")
	private String legpersonType;
	@ApiModelProperty(name = "legpersonId" , value = "法人证件号码")
	private String legpersonId;
	@ApiModelProperty(name = "businessFile" , value = "营业执照副本")
	private String businessFile;
	@ApiModelProperty(name = "registerFile" , value = "注册商标")
	private String registerFile;
	@ApiModelProperty(name = "certificate" , value = "授权书")
	private String certificate;
	@ApiModelProperty(name = "reviewId" , value = "审核人id")
	private Long reviewId;
	@ApiModelProperty(name = "reviewStatus" , value = "审批状态：0-未审核，1-审核通过，2-审核不通过")
	private String reviewStatus;
	@ApiModelProperty(name = "reviewRemarks" , value = "审批备注")
	private String reviewRemarks;
	@ApiModelProperty(name = "status" , value = "删除标志")
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
