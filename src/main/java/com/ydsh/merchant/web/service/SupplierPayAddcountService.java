/**
 * @filename:SupplierPayAddcountService 2019-06-19 10:46:46
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ydsh.merchant.web.entity.SupplierPayAddcount;
import com.ydsh.merchant.web.entity.dto.SupplierPayAddcountAndDataDto;
import com.ydsh.merchant.web.entity.dto.SupplierPayAddcountQueryDto;
/**   
 * <p>自定义service写在这里</p>
 * 
 * <p>说明： 供应商充值记录表服务层</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
public interface SupplierPayAddcountService extends IService<SupplierPayAddcount> {
	
	/**
	 * 
	* *充值记录表和供应商表连表查询
	*
	* @param @param page
	* @param @param queryWrapper
	* @param @return
	* @return
	 */
	  Page<SupplierPayAddcountAndDataDto> selectPayAddcountPage(IPage<Map<String, Object>> page,@Param("queryWrapper") SupplierPayAddcountQueryDto queryWrapper);
}