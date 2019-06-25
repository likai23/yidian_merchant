/**
 * @filename:CustomerPayReducecountController 2019-06-19 11:55:28
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydsh.generator.common.JsonResult;
import com.ydsh.generator.common.PageParam;
import com.ydsh.merchant.web.controller.base.AbstractController;
import com.ydsh.merchant.web.entity.CustomerPayReducecount;
import com.ydsh.merchant.web.entity.dto.CustomerDataAndPayReduceacount;
import com.ydsh.merchant.web.entity.dto.CustomerPayReduceacountQueryDto;
import com.ydsh.merchant.web.service.CustomerPayReducecountService;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
/**   
 * <p>自定义方法写在这里</p>
 * 
 * <p>说明： 客户退款记录表API接口层</P>
 * @version: V1.0
 * @author: 戴艺辉
 *
 */
@Api(description = "客户退款记录表",value="客户退款记录表" )
@RestController
@RequestMapping("/customerPayReducecount")
@Slf4j
public class CustomerPayReducecountController extends AbstractController<CustomerPayReducecountService,CustomerPayReducecount>{
	/**
	 * @explain 分页条件查询用户   
	 * @param   param
	 * @return  JsonResult<IPage<T>>
	 * @author  戴艺辉
	 * @time    2019-06-11 09:49:42
	 */
    @RequestMapping(value = "/selectCustomerPayReducePageIndex",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：戴艺辉")
	public JsonResult<IPage<CustomerDataAndPayReduceacount>> selectCustomerPayReducePageIndex(PageParam<CustomerPayReduceacountQueryDto> param){
		JsonResult<IPage<CustomerDataAndPayReduceacount>> returnPage=new JsonResult<IPage<CustomerDataAndPayReduceacount>>();
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(param.getPageNum(),param.getPageSize());
		QueryWrapper<CustomerPayReduceacountQueryDto> queryWrapper =new QueryWrapper<CustomerPayReduceacountQueryDto>();
		queryWrapper.setEntity(param.getParam());
		if(param.getParam()==null) {
			queryWrapper.setEntity(new CustomerPayReduceacountQueryDto());
		}
		//分页数据
		IPage<CustomerDataAndPayReduceacount> pageData=baseService.selectCustomerPayReducePageIndex(page, queryWrapper.getEntity());
		returnPage.success(pageData);
		return returnPage;
	}
}