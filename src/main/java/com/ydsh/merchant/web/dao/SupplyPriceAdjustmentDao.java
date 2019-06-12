/**
 * @filename:SupplyPriceAdjustmentDao 2019-06-11 09:49:43
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.ydsh.merchant.web.entity.SupplyPriceAdjustment;

/**   
 * <p>自定义mapper写在这里</p>
 * 
 * <p>说明： 供应价调整单数据访问层</p>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Mapper
public interface SupplyPriceAdjustmentDao extends BaseMapper<SupplyPriceAdjustment> {
	
}
