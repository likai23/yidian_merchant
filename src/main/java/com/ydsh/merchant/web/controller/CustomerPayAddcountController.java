/**
 * @filename:CustomerPayAddcountController 2019-06-19 11:55:28
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

import com.ydsh.merchant.web.controller.base.AbstractController;
import com.ydsh.merchant.web.entity.CustomerPayAddcount;
import com.ydsh.merchant.web.service.CustomerPayAddcountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
/**   
 * <p>自定义方法写在这里</p>
 * 
 * <p>说明： 客户充值记录表API接口层</P>
 * @version: V1.0
 * @author: 戴艺辉
 *
 */
@Api(description = "客户充值记录表",value="客户充值记录表" )
@RestController
@RequestMapping("/customerPayAddcount")
@Slf4j
public class CustomerPayAddcountController extends AbstractController<CustomerPayAddcountService,CustomerPayAddcount>{
	
}