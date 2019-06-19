/**
 * @filename:SupplierPayAddcountServiceImpl 2019-06-19 10:46:46
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2018 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.service.impl;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydsh.merchant.web.dao.SupplierPayAddcountDao;
import com.ydsh.merchant.web.entity.SupplierPayAddcount;
import com.ydsh.merchant.web.entity.dto.SupplierPayAddcountAndDataDto;
import com.ydsh.merchant.web.entity.dto.SupplierPayAddcountQueryDto;
import com.ydsh.merchant.web.service.SupplierPayAddcountService;

/**   
 * <p>自定义serviceImpl写在这里</p>
 * 
 * <p>说明： 供应商充值记录表服务实现层</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Service
public class SupplierPayAddcountServiceImpl  extends ServiceImpl<SupplierPayAddcountDao, SupplierPayAddcount> implements SupplierPayAddcountService  {
	/**
	 * 
	* *充值记录表和供应商表连表查询
	*
	* @param @param page
	* @param @param queryWrapper
	* @param @return
	* @return
	 */
	  public Page<SupplierPayAddcountAndDataDto> selectPayAddcountPage(IPage<Map<String, Object>> page,@Param("queryWrapper") SupplierPayAddcountQueryDto queryWrapper){
		  return baseMapper.selectPayAddcountPage(page, queryWrapper);
	  }
}