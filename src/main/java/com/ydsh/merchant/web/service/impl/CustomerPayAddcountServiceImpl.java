/**
 * @filename:CustomerPayAddcountServiceImpl 2019-06-19 11:55:28
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2018 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.service.impl;

import com.ydsh.merchant.web.entity.CustomerPayAddcount;
import com.ydsh.merchant.web.dao.CustomerPayAddcountDao;
import com.ydsh.merchant.web.service.CustomerPayAddcountService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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
	
}