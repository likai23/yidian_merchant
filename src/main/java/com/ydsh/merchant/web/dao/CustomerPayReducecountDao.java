/**
 * @filename:CustomerPayReducecountDao 2019-06-19 11:55:28
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydsh.merchant.web.entity.CustomerPayReducecount;
import com.ydsh.merchant.web.entity.dto.CustomerDataAndPayReduceacount;
import com.ydsh.merchant.web.entity.dto.CustomerPayReduceacountQueryDto;

/**   
 * <p>自定义mapper写在这里</p>
 * 
 * <p>说明： 客户退款记录表数据访问层</p>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Mapper
public interface CustomerPayReducecountDao extends BaseMapper<CustomerPayReducecount> {
	/**
	 * 
	* *充值记录表和客户表连表查询
	*
	* @param @param page
	* @param @param queryWrapper
	* @param @return
	* @return
	 */
	  Page<CustomerDataAndPayReduceacount> selectCustomerPayReducePageIndex(IPage<Map<String, Object>> page,@Param("queryWrapper") CustomerPayReduceacountQueryDto queryWrapper);
}
