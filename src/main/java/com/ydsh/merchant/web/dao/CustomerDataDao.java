/**
 * @filename:CustomerDataDao 2019-06-11 09:49:42
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.ydsh.merchant.web.entity.CustomerData;

/**   
 * <p>自定义mapper写在这里</p>
 * 
 * <p>说明： 客户基本资料表数据访问层</p>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Mapper
public interface CustomerDataDao extends BaseMapper<CustomerData> {
	
}
