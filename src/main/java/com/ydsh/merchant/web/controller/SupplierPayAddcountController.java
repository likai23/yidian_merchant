/**
 * @filename:SupplierPayAddcountController 2019-06-19 10:46:46
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
import com.ydsh.merchant.web.entity.SupplierPayAddcount;
import com.ydsh.merchant.web.entity.dto.SupplierPayAddcountAndDataDto;
import com.ydsh.merchant.web.entity.dto.SupplierPayAddcountDto;
import com.ydsh.merchant.web.entity.dto.SupplierPayAddcountQueryDto;
import com.ydsh.merchant.web.service.SupplierPayAddcountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 自定义方法写在这里
 * </p>
 * 
 * <p>
 * 说明： 供应商充值记录表API接口层
 * </P>
 * 
 * @version: V1.0
 * @author: 戴艺辉
 *
 */
@Api(description = "供应商充值记录表", value = "供应商充值记录表")
@RestController
@RequestMapping("/supplierPayAddcount")
@Slf4j
public class SupplierPayAddcountController extends AbstractController<SupplierPayAddcountService, SupplierPayAddcount> {

	private static Timestamp now = new Timestamp(System.currentTimeMillis());

	private static Logger logger = LoggerFactory.getLogger(CustomerDataController.class);

	
	/**
	 * @explain 分页条件查询用户   
	 * @param   param
	 * @return  JsonResult<IPage<T>>
	 * @author  戴艺辉
	 * @time    2019-06-11 09:49:42
	 */
    @RequestMapping(value = "/selectPayAddcountPage",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：戴艺辉")
	public JsonResult<IPage<SupplierPayAddcountAndDataDto>> selectPayAddcountPage(PageParam<SupplierPayAddcountQueryDto> param){
		JsonResult<IPage<SupplierPayAddcountAndDataDto>> returnPage=new JsonResult<IPage<SupplierPayAddcountAndDataDto>>();
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(param.getPageNum(),param.getPageSize());
		QueryWrapper<SupplierPayAddcountQueryDto> queryWrapper =new QueryWrapper<SupplierPayAddcountQueryDto>();
		queryWrapper.setEntity(param.getParam());
		if(param.getParam()==null) {
			queryWrapper.setEntity(new SupplierPayAddcountQueryDto());
		}
		//分页数据
		IPage<SupplierPayAddcountAndDataDto> pageData=baseService.selectPayAddcountPage(page, queryWrapper.getEntity());
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
	@RequestMapping(value = "/saveSupplierPayAddcount", method = RequestMethod.POST)
	@ApiOperation(value = "添加", notes = "作者：戴艺辉")
	public JsonResult<Object> saveSupplierPayAddcount(@RequestBody SupplierPayAddcountDto entity) {
		JsonResult<Object> result = new JsonResult<Object>();
		if (null != entity) {
			String accountBook=entity.getAccountBook();
			String recharge=entity.getRecharge();
			String rechargeWay=entity.getRechargeWay();
			if (TextUtils.isEmptys(accountBook,recharge,rechargeWay)) {
				logger.info("参数为空");
				throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
			}
			SupplierPayAddcount supplierPayAddcount = new SupplierPayAddcount();
			BeanUtils.copyProperties(entity, supplierPayAddcount);
			supplierPayAddcount.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
			supplierPayAddcount
					.setRecharge(new BigDecimal(entity.getRecharge()).multiply(new BigDecimal("10000")).longValue());
			boolean rsg = baseService.save(supplierPayAddcount);
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
	@RequestMapping(value = "/reviewSupplierPayAddcount", method = RequestMethod.POST)
	@ApiOperation(value = "审核充值记录", notes = "作者：戴艺辉")
	public JsonResult<Object> reviewSupplierPayAddcount(@RequestBody SupplierPayAddcount entity) {
		JsonResult<Object> result = new JsonResult<Object>();
		String id = String.valueOf(entity.getId());
		String reviewStatus = entity.getReviewStatus();
		if (TextUtils.isEmptys(id, reviewStatus)) {
			logger.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		SupplierPayAddcount supplierPayAddcount = baseService.getById(id);
		// 待审核状态才允许审核
		if ((supplierPayAddcount.getReviewStatus()).equals(DBDictionaryEnumManager.review_0.getkey())) {
			entity.setReviewTime(now);
			baseService.updateById(entity);
			result.success("审核成功！");
			return result;
		} else {
			logger.info("不是待审核状态不允许审核！");
			result.error("不是待审核状态不允许审核！");
			return result;
		}
	}
}