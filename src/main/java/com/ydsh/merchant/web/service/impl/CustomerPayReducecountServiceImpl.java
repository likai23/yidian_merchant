/**
 * @filename:CustomerPayReducecountServiceImpl 2019-06-19 11:55:28
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2018 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.service.impl;

import com.ydsh.merchant.web.entity.CustomerPayReducecount;
import com.ydsh.merchant.web.entity.dto.CustomerDataAndPayReduceacount;
import com.ydsh.merchant.web.entity.dto.CustomerPayReduceacountQueryDto;
import com.ydsh.merchant.web.dao.CustomerPayReducecountDao;
import com.ydsh.merchant.web.service.CustomerPayReducecountService;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**   
 * <p>自定义serviceImpl写在这里</p>
 * 
 * <p>说明： 客户退款记录表服务实现层</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Service
public class CustomerPayReducecountServiceImpl  extends ServiceImpl<CustomerPayReducecountDao, CustomerPayReducecount> implements CustomerPayReducecountService  {
	/**
	 * 
	* *充值记录表和客户表连表查询
	*
	* @param @param page
	* @param @param queryWrapper
	* @param @return
	* @return
	 */
	  public Page<CustomerDataAndPayReduceacount> selectCustomerPayReducePageIndex(IPage<Map<String, Object>> page,@Param("queryWrapper") CustomerPayReduceacountQueryDto queryWrapper){
		  return baseMapper.selectCustomerPayReducePageIndex(page, queryWrapper);
	  }
}