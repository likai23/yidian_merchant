/**
 * @filename:SupplierPayReducecountServiceImpl 2019-06-19 10:47:08
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2018 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.service.impl;

import com.ydsh.merchant.web.entity.SupplierPayReducecount;
import com.ydsh.merchant.web.entity.dto.SupplierPayReducecountAndDataDto;
import com.ydsh.merchant.web.entity.dto.SupplierPayReducecountQueryDto;
import com.ydsh.merchant.web.dao.SupplierPayReducecountDao;
import com.ydsh.merchant.web.service.SupplierPayReducecountService;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**   
 * <p>自定义serviceImpl写在这里</p>
 * 
 * <p>说明： 供应商退款记录表服务实现层</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Service
public class SupplierPayReducecountServiceImpl  extends ServiceImpl<SupplierPayReducecountDao, SupplierPayReducecount> implements SupplierPayReducecountService  {
	
	/**
	 * 
	* *退款记录表和供应商表连表查询
	*
	* @param @param page
	* @param @param queryWrapper
	* @param @return
	* @return
	 */
	  public Page<SupplierPayReducecountAndDataDto> selectPayReducePage(IPage<Map<String, Object>> page,@Param("queryWrapper") SupplierPayReducecountQueryDto queryWrapper){
		  return baseMapper.selectPayReducePage(page, queryWrapper);
	  }
}