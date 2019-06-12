/**
 * @filename:SupplierDataServiceImpl 2019-06-11 09:49:43
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2018 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.service.impl;

import com.ydsh.merchant.web.entity.SupplierData;
import com.ydsh.merchant.web.dao.SupplierDataDao;
import com.ydsh.merchant.web.service.SupplierDataService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**   
 * <p>自定义serviceImpl写在这里</p>
 * 
 * <p>说明： 供应基础信息表服务实现层</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Service
public class SupplierDataServiceImpl  extends ServiceImpl<SupplierDataDao, SupplierData> implements SupplierDataService  {
	
}