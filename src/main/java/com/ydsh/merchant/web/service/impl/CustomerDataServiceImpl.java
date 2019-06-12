/**
 * @filename:CustomerDataServiceImpl 2019-06-11 09:49:42
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2018 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.service.impl;

import com.ydsh.merchant.web.entity.CustomerData;
import com.ydsh.merchant.web.dao.CustomerDataDao;
import com.ydsh.merchant.web.service.CustomerDataService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**   
 * <p>自定义serviceImpl写在这里</p>
 * 
 * <p>说明： 客户基本资料表服务实现层</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Service
public class CustomerDataServiceImpl  extends ServiceImpl<CustomerDataDao, CustomerData> implements CustomerDataService  {
	
}