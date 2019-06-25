/**
 * @filename:CustomerPayAddcountService 2019-06-19 11:55:28
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
import com.ydsh.merchant.web.entity.CustomerPayAddcount;
import com.ydsh.merchant.web.entity.dto.CustomerDataAndPayAddcount;
import com.ydsh.merchant.web.entity.dto.CustomerPayAddcountQueryDto;
/**   
 * <p>自定义service写在这里</p>
 * 
 * <p>说明： 客户充值记录表服务层</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
public interface CustomerPayAddcountService extends IService<CustomerPayAddcount> {
	/**
	 * 
	* *充值记录表和客户表连表查询
	*
	* @param @param page
	* @param @param queryWrapper
	* @param @return
	* @return
	 */
	  Page<CustomerDataAndPayAddcount> selectCustomerPayAddPageIndex(IPage<Map<String, Object>> page,@Param("queryWrapper") CustomerPayAddcountQueryDto queryWrapper);
}