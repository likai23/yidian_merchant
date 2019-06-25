/**
 * @filename:CustomerPayAddcountController 2019-06-19 11:55:28
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydsh.generator.common.JsonResult;
import com.ydsh.generator.common.PageParam;
import com.ydsh.merchant.common.enums.DBDictionaryEnumManager;
import com.ydsh.merchant.common.enums.ErrorCode;
import com.ydsh.merchant.common.exception.SystemException;
import com.ydsh.merchant.common.util.TextUtils;
import com.ydsh.merchant.web.controller.base.AbstractController;
import com.ydsh.merchant.web.entity.CustomerPayAddcount;
import com.ydsh.merchant.web.entity.dto.CustomerDataAndPayAddcount;
import com.ydsh.merchant.web.entity.dto.CustomerPayAddcountDto;
import com.ydsh.merchant.web.entity.dto.CustomerPayAddcountQueryDto;
import com.ydsh.merchant.web.entity.dto.reviewCustomerPayAddcountDto;
import com.ydsh.merchant.web.service.CustomerPayAddcountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

	private static Logger logger = LoggerFactory.getLogger(CustomerPayAddcountController.class);

	private static Timestamp now = new Timestamp(System.currentTimeMillis());
	
	/**
	 * @explain 分页条件查询用户   
	 * @param   param
	 * @return  JsonResult<IPage<T>>
	 * @author  戴艺辉
	 * @time    2019-06-11 09:49:42
	 */
    @RequestMapping(value = "/selectPayAddcountPage",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：戴艺辉")
	public JsonResult<IPage<CustomerDataAndPayAddcount>> selectPayAddcountPage(PageParam<CustomerPayAddcountQueryDto> param){
		JsonResult<IPage<CustomerDataAndPayAddcount>> returnPage=new JsonResult<IPage<CustomerDataAndPayAddcount>>();
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(param.getPageNum(),param.getPageSize());
		QueryWrapper<CustomerPayAddcountQueryDto> queryWrapper =new QueryWrapper<CustomerPayAddcountQueryDto>();
		queryWrapper.setEntity(param.getParam());
		if(param.getParam()==null) {
			queryWrapper.setEntity(new CustomerPayAddcountQueryDto());
		}
		//分页数据
		IPage<CustomerDataAndPayAddcount> pageData=baseService.selectCustomerPayAddPageIndex(page, queryWrapper.getEntity());
		returnPage.success(pageData);
		return returnPage;
	}
		/**
		 * @explain 添加
		 * @param entity
		 * @return JsonResult
		 * @author 戴艺辉
		 * @time 2019-06-11 09:49:42
		 */
		@RequestMapping(value = "/saveCustomerPayAddcount", method = RequestMethod.POST)
		@ApiOperation(value = "添加", notes = "作者：戴艺辉")
		public JsonResult<Object> saveCustomerPayAddcount(@RequestBody CustomerPayAddcountDto entity) {
			JsonResult<Object> result = new JsonResult<Object>();
			if (null != entity) {
				String accountBook=entity.getAccountBook();
				String recharge=entity.getRechargeAmount();
				String rechargeWay=entity.getRechargeWay();
				if (TextUtils.isEmptys(accountBook,recharge,rechargeWay)) {
					logger.info("参数为空");
					throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
				}
				CustomerPayAddcount customerPayAddcount = new CustomerPayAddcount();
				BeanUtils.copyProperties(entity, customerPayAddcount);
				customerPayAddcount.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
				customerPayAddcount.setRechargeAmount(new BigDecimal(entity.getRechargeAmount()).multiply(new BigDecimal("10000")).longValue());
				boolean rsg = baseService.save(customerPayAddcount);
				if (rsg) {
					result.success("添加成功");
				} else {
					result.error("添加失败！");
				}
			} else {
				result.error("请传入正确参数！");
			}
			return result;
		}

		/**
		 * @explain 审核
		 * @param entity
		 * @return JsonResult
		 * @author 戴艺辉
		 * @time 2019-06-11 09:49:42
		 */
		@RequestMapping(value = "/reviewCustomerPayAddcount", method = RequestMethod.POST)
		@ApiOperation(value = "审核充值记录", notes = "作者：戴艺辉")
		public JsonResult<Object> reviewCustomerPayAddcount(@RequestBody reviewCustomerPayAddcountDto entity) {
			JsonResult<Object> result = new JsonResult<Object>();
			String id = String.valueOf(entity.getId());
			String reviewStatus = entity.getReviewStatus();
			if (TextUtils.isEmptys(id, reviewStatus)) {
				logger.info("参数为空");
				throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
			}
			CustomerPayAddcount customerPayAddcount = baseService.getById(id);
			// 待审核状态才允许审核
			if ((customerPayAddcount.getReviewStatus()).equals(DBDictionaryEnumManager.review_0.getkey())) {
				CustomerPayAddcount c=new CustomerPayAddcount();
				c.setId(entity.getId());
				c.setReviewStatus(entity.getReviewStatus());
				c.setReviewRemarks(entity.getReviewRemarks());
				c.setReviewTime(now);
				baseService.updateById(c);
				result.success("审核成功！");
				return result;
			} else {
				logger.info("不是待审核状态不允许审核！");
				result.error("不是待审核状态不允许审核！");
				return result;
			}
		}
}