/**
 * @filename:SupplierPayReducecountService 2019-06-19 10:47:08
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.service;

import com.ydsh.merchant.web.entity.SupplierPayReducecount;
import com.ydsh.merchant.web.entity.dto.SupplierPayReducecountAndDataDto;
import com.ydsh.merchant.web.entity.dto.SupplierPayReducecountQueryDto;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * <p>自定义service写在这里</p>
 * 
 * <p>说明： 供应商退款记录表服务层</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
public interface SupplierPayReducecountService extends IService<SupplierPayReducecount> {
	
	
	/**
	 * 
	* *退款记录表和供应商表连表查询
	*
	* @param @param page
	* @param @param queryWrapper
	* @param @return
	* @return
	 */
	  Page<SupplierPayReducecountAndDataDto> selectPayReducePage(IPage<Map<String, Object>> page,@Param("queryWrapper") SupplierPayReducecountQueryDto queryWrapper);
}