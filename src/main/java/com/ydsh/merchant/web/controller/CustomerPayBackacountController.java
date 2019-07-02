/**
 * @filename:CustomerPayBackacountController 2019-06-19 11:55:28
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

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
import com.ydsh.merchant.common.exception.BizException;
import com.ydsh.merchant.common.util.TextUtils;
import com.ydsh.merchant.web.controller.base.AbstractController;
import com.ydsh.merchant.web.entity.CustomerPayBackacount;
import com.ydsh.merchant.web.entity.dto.CustomerDataAndPayBackacount;
import com.ydsh.merchant.web.entity.dto.CustomerPayAddcountQueryDto;
import com.ydsh.merchant.web.entity.dto.CustomerPayBackacountDto;
import com.ydsh.merchant.web.entity.dto.reviewCustomerPayBackacountDto;
import com.ydsh.merchant.web.service.CustomerPayBackacountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 自定义方法写在这里
 * </p>
 * 
 * <p>
 * 说明： 客户回款记录表API接口层
 * </P>
 * 
 * @version: V1.0
 * @author: 戴艺辉
 *
 */
@Api(description = "客户回款记录表", value = "客户回款记录表")
@RestController
@RequestMapping("/customerPayBackacount")
@Slf4j
public class CustomerPayBackacountController
		extends AbstractController<CustomerPayBackacountService, CustomerPayBackacount> {


	private static Timestamp now = new Timestamp(System.currentTimeMillis());

	/**
	 * @explain 分页条件查询用户
	 * @param param
	 * @return JsonResult<IPage<T>>
	 * @author 戴艺辉
	 * @time 2019-06-11 09:49:42
	 */
	@RequestMapping(value = "/selectCustomerPayBackPageIndex", method = RequestMethod.GET)
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：戴艺辉")
	public JsonResult<IPage<CustomerDataAndPayBackacount>> selectCustomerPayBackPageIndex(
			PageParam<CustomerPayAddcountQueryDto> param) {
		JsonResult<IPage<CustomerDataAndPayBackacount>> returnPage = new JsonResult<IPage<CustomerDataAndPayBackacount>>();
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(param.getPageNum(), param.getPageSize());
		QueryWrapper<CustomerPayAddcountQueryDto> queryWrapper = new QueryWrapper<CustomerPayAddcountQueryDto>();
		queryWrapper.setEntity(param.getParam());
		if (param.getParam() == null) {
			queryWrapper.setEntity(new CustomerPayAddcountQueryDto());
		}
		// 分页数据
		IPage<CustomerDataAndPayBackacount> pageData = baseService.selectCustomerPayBackPageIndex(page,
				queryWrapper.getEntity());
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
	@RequestMapping(value = "/saveCustomerPayBackacount", method = RequestMethod.POST)
	@ApiOperation(value = "添加回款记录", notes = "作者：戴艺辉")
	public JsonResult<Object> saveCustomerPayBackacount(@RequestBody CustomerPayBackacountDto entity) {
		JsonResult<Object> result = new JsonResult<Object>();
		if (null != entity) {
			String accountBook = entity.getAccountBook();
			String recharge = entity.getRecharge();
			String rechargeWay = entity.getRechargeWay();
			if (TextUtils.isEmptys(accountBook, recharge, rechargeWay)) {
				log.info("参数为空");
				throw new BizException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空");
			}
			CustomerPayBackacount customerPayBackacount = new CustomerPayBackacount();
			BeanUtils.copyProperties(entity, customerPayBackacount);
			customerPayBackacount.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
			customerPayBackacount
					.setRecharge(new BigDecimal(entity.getRecharge()).multiply(new BigDecimal("10000")).longValue());
			boolean rsg = baseService.save(customerPayBackacount);
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
	@ApiOperation(value = "审核回款记录", notes = "作者：戴艺辉")
	public JsonResult<Object> reviewCustomerPayAddcount(@RequestBody reviewCustomerPayBackacountDto entity) {
		JsonResult<Object> result = new JsonResult<Object>();
		String id = String.valueOf(entity.getId());
		String cdId = String.valueOf(entity.getCdId());
		String reviewStatus = entity.getReviewStatus();
		if (TextUtils.isEmptys(id, cdId, reviewStatus)) {
			log.info("参数为空");
			throw new BizException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空");
		}
		CustomerPayBackacount customerPayBackacount = baseService.getById(id);
		// 待审核状态才允许审核
		if ((customerPayBackacount.getReviewStatus()).equals(DBDictionaryEnumManager.review_0.getkey())) {
			CustomerPayBackacount c = new CustomerPayBackacount();
			c.setId(entity.getId());
			c.setReviewStatus(entity.getReviewStatus());
			c.setReviewRemarks(entity.getReviewRemarks());
			c.setReviewTime(now);
			baseService.updateById(c);
			result.success("审核成功！");
			return result;
		} else {
			log.info("不是待审核状态不允许审核！");
			result.error("不是待审核状态不允许审核！");
			return result;
		}
	}
}