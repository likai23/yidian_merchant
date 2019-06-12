/**
 * @filename:SupplyPriceAdjustmentController 2019-06-11 09:49:43
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

import com.ydsh.merchant.web.controller.base.AbstractController;
import com.ydsh.merchant.web.entity.SupplyPriceAdjustment;
import com.ydsh.merchant.web.service.SupplyPriceAdjustmentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
/**   
 * <p>自定义方法写在这里</p>
 * 
 * <p>说明： 供应价调整单API接口层</P>
 * @version: V1.0
 * @author: 戴艺辉
 *
 */
@Api(description = "供应价调整单",value="供应价调整单" )
@RestController
@RequestMapping("/supplyPriceAdjustment")
@Slf4j
public class SupplyPriceAdjustmentController extends AbstractController<SupplyPriceAdjustmentService,SupplyPriceAdjustment>{
	
}