/**
 * @filename:CustomerPayBackacountDao 2019-06-19 11:55:28
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.ydsh.merchant.web.entity.CustomerPayBackacount;

/**   
 * <p>自定义mapper写在这里</p>
 * 
 * <p>说明： 客户回款记录表数据访问层</p>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Mapper
public interface CustomerPayBackacountDao extends BaseMapper<CustomerPayBackacount> {
	
}
