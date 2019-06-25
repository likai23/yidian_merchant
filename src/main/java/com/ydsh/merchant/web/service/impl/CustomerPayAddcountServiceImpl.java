/**
 * @filename:CustomerPayAddcountServiceImpl 2019-06-19 11:55:28
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
import com.ydsh.merchant.web.dao.CustomerPayAddcountDao;
import com.ydsh.merchant.web.entity.CustomerPayAddcount;
import com.ydsh.merchant.web.entity.dto.CustomerDataAndPayAddcount;
import com.ydsh.merchant.web.entity.dto.CustomerPayAddcountQueryDto;
import com.ydsh.merchant.web.service.CustomerPayAddcountService;

/**   
 * <p>自定义serviceImpl写在这里</p>
 * 
 * <p>说明： 客户充值记录表服务实现层</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Service
public class CustomerPayAddcountServiceImpl  extends ServiceImpl<CustomerPayAddcountDao, CustomerPayAddcount> implements CustomerPayAddcountService  {
	/**
	 * 
	* *充值记录表和客户表连表查询
	*
	* @param @param page
	* @param @param queryWrapper
	* @param @return
	* @return
	 */
	  public Page<CustomerDataAndPayAddcount> selectCustomerPayAddPageIndex(IPage<Map<String, Object>> page,@Param("queryWrapper") CustomerPayAddcountQueryDto queryWrapper){
		  return baseMapper.selectCustomerPayAddPageIndex(page, queryWrapper);
	  }
}