/**
 * @filename:SupplierDataController 2019-06-11 09:49:43
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydsh.generator.common.JsonResult;
import com.ydsh.generator.common.PageParam;
import com.ydsh.merchant.common.db.DBKeyGenerator;
import com.ydsh.merchant.common.enums.DBBusinessKeyTypeEnums;
import com.ydsh.merchant.common.enums.DBDictionaryEnumManager;
import com.ydsh.merchant.common.enums.ErrorCode;
import com.ydsh.merchant.common.enums.SuccessCode;
import com.ydsh.merchant.common.exception.SystemException;
import com.ydsh.merchant.common.util.TextUtils;
import com.ydsh.merchant.web.controller.base.AbstractController;
import com.ydsh.merchant.web.entity.CustomerData;
import com.ydsh.merchant.web.entity.SupplierData;
import com.ydsh.merchant.web.service.CustomerDataService;
import com.ydsh.merchant.web.service.SupplierDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 自定义方法写在这里
 * </p>
 * 
 * <p>
 * 说明： 供应基础信息表API接口层
 * </P>
 * 
 * @version: V1.0
 * @author: 戴艺辉
 *
 */
@Api(description = "供应基础信息表", value = "供应基础信息表")
@RestController
@RequestMapping("/supplierData")
@Slf4j
public class SupplierDataController extends AbstractController<SupplierDataService, SupplierData> {

	private static Logger logger = LoggerFactory.getLogger(SupplierDataController.class);

	/**
	 * @explain 添加
	 * @param entity
	 * @return JsonResult
	 * @author 戴艺辉
	 * @time 2019-06-11 09:49:42
	 */
	@RequestMapping(value = "/saveSupplier", method = RequestMethod.POST)
	@ApiOperation(value = "添加", notes = "作者：戴艺辉")
	public JsonResult<Object> saveSupplier(@RequestBody SupplierData entity) {
		String supplierName = entity.getSupplierName();

		String trade = entity.getTrade();
		String type = entity.getType();
		String legperson = entity.getLegperson();
		String reqperson = entity.getReqperson();
		String province = entity.getProvince();
		String city = entity.getCity();
		String reqPhone = entity.getReqPhone();
		String forshort = entity.getForshort();
		String logo = entity.getLogo();
		if (TextUtils.isEmptys(supplierName, trade, type, legperson, reqperson, province, city, reqPhone, forshort,
				logo)) {
			logger.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		// 供应商编码
		String sdNo = DBKeyGenerator.generatorDBKey(DBBusinessKeyTypeEnums.S, trade);
		JsonResult<Object> result = new JsonResult<Object>();
		if (null != entity) {
			entity.setSdNo(sdNo);
			boolean rsg = baseService.save(entity);
			if (rsg) {
				result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
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
	 * @explain 修改
	 * @param entity
	 * @return JsonResult
	 * @author 戴艺辉
	 * @time 2019-06-11 09:49:42
	 */
	@RequestMapping(value = "/updateSupplier", method = RequestMethod.POST)
	@ApiOperation(value = "修改", notes = "作者：戴艺辉")
	public JsonResult<Object> updateSupplier(@RequestBody SupplierData entity) {
		JsonResult<Object> result = new JsonResult<Object>();
		String sdNo = entity.getSdNo();
		String supplierName = entity.getSupplierName();
		String trade = entity.getTrade();
		String type = entity.getType();
		String legperson = entity.getLegperson();
		String reqperson = entity.getReqperson();
		String province = entity.getProvince();
		String city = entity.getCity();
		String reqPhone = entity.getReqPhone();
		String forshort = entity.getForshort();
		String logo = entity.getLogo();
		if (TextUtils.isEmptys(sdNo, supplierName, trade, type, legperson, reqperson, province, city, reqPhone,
				forshort, logo)) {
			logger.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		if (null != entity) {
			entity.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
			boolean rsg = baseService.updateById(entity);
			if (rsg) {
				result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
				result.success("修改成功！");
			} else {
				result.error("修改失败！");
			}
		} else {
			result.error("请传入正确参数！");
		}
		return result;
	}

	/**
	 * @explain 1-修改供应商状态(启用/禁用)，2-审核供应商，3-删除
	 * @param entity
	 * @return JsonResult
	 * @author 戴艺辉
	 * @time 2019-06-11 09:49:42
	 */
	@RequestMapping(value = "/updateSupplierStatus", method = RequestMethod.POST)
	@ApiOperation(value = "修改供应商状态", notes = "作者：戴艺辉")
	public JsonResult<Object> updateSupplier(@RequestBody Map<String, Object> param) {
		JsonResult<Object> result = new JsonResult<Object>();
		String updateSign = TextUtils.getMapForKeyToString(param, "updateSign");
		if (TextUtils.isEmpty(updateSign)) {
			logger.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		// 启用和禁用供应商
		if (updateSign.equals("updateSupplierStatus")) {
			String id = TextUtils.getMapForKeyToString(param, "id");
			String supplierStatus = TextUtils.getMapForKeyToString(param, "supplierStatus");
			if (TextUtils.isEmptys(id, supplierStatus)) {
				logger.info("参数为空");
				throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
			}
			SupplierData supplierDataCheck = baseService.getById(Long.parseLong(id));
			if (supplierDataCheck == null) {
				logger.info("参数异常");
				throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
			}
			// 启用
			if (supplierStatus.equals(DBDictionaryEnumManager.user_status_0.getkey())) {
				if (!(supplierDataCheck.getSupplierStatus().equals(DBDictionaryEnumManager.user_status_1.getkey()))) {
					logger.info("不是禁用状态，不能启用");
					throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "不是禁用状态，不能启用", new Exception());
				}
				SupplierData supplierData = new SupplierData();
				supplierData.setId(Long.parseLong(id));
				supplierData.setSupplierStatus(supplierStatus);
				baseService.updateById(supplierData);
				result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
				result.setMessage("更新状态成功！");
			}
			// 禁用
			else if (supplierStatus.equals(DBDictionaryEnumManager.user_status_1.getkey())) {
				if (!(supplierDataCheck.getSupplierStatus().equals(DBDictionaryEnumManager.user_status_0.getkey()))) {
					logger.info("不是启用状态");
					throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "不是启用状态，不能禁用", new Exception());
				}
				SupplierData supplierData = new SupplierData();
				supplierData.setId(Long.parseLong(id));
				supplierData.setSupplierStatus(supplierStatus);
				baseService.updateById(supplierData);
				result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
				result.setMessage("更新状态成功！");
			} else {
				logger.info("参数异常");
				throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
			}
		}
		// 审核
		else if (updateSign.equals("reviewSupplier")) {
			String id = TextUtils.getMapForKeyToString(param, "id");
			String reviewStatus = TextUtils.getMapForKeyToString(param, "reviewStatus");
			String reviewRemarks = TextUtils.getMapForKeyToString(param, "reviewBz");
			if (TextUtils.isEmptys(id, reviewStatus)) {
				logger.info("参数为空");
				throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
			}
			SupplierData supplierData = new SupplierData();
			supplierData.setId(Long.parseLong(id));
			supplierData.setReviewStatus(reviewStatus);
			supplierData.setReviewRemarks(reviewRemarks);
			baseService.updateById(supplierData);
			result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
			result.setMessage("审核成功！");
		} 
		//删除
		else if(updateSign.equals("removeSupplier")) {
			String id = TextUtils.getMapForKeyToString(param, "id");
			SupplierData supplierData = new SupplierData();
			supplierData.setId(Long.parseLong(id));
			supplierData.setStatus(DBDictionaryEnumManager.invalid.getkey());
			baseService.updateById(supplierData);
			result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
			result.setMessage("删除成功！");
		}
		else {
			logger.info("参数异常");
			throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
		}
		return result;
	}

	/**
	 * 
	 * 1-查看供应商 2-修改时进入查看供应商
	 * 
	 * @param @param  param
	 * @param @return
	 * @return
	 */
	@RequestMapping(value = "/getSupplierById", method = RequestMethod.POST)
	@ApiOperation(value = "获取供应商基本信息", notes = "作者：戴艺辉")
	public JsonResult<Object> getSupplierById(@RequestBody Map<String, Object> param) {
		JsonResult<Object> result = new JsonResult<Object>();
		String id = TextUtils.getMapForKeyToString(param, "id");
		String getSign = TextUtils.getMapForKeyToString(param, "getSign");
		if (TextUtils.isEmptys(id, getSign)) {
			logger.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		// 查看
		if (getSign.equals("lookSupplier")) {
			SupplierData supplierData = baseService.getById(id);
			result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
			result.setMessage("查询成功！");
			result.setData(supplierData);
		}
		// 修改时进入查看
		else if (getSign.equals("lookSupplierWithStatus")) {
			SupplierData supplierData = baseService.getById(id);
			//只有待审核和审核不通过才能修改
			if ((supplierData.getReviewStatus()).equals(DBDictionaryEnumManager.review_0.getkey())
					|| (supplierData.getReviewStatus()).equals(DBDictionaryEnumManager.review_2.getkey())) {
				result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
				result.setMessage("查询成功！");
				result.setData(supplierData);
			}else {
				result.setCode(String.valueOf(ErrorCode.SYS_EXCEPTION.getCode()));
				result.setMessage("查询失败！");
			}
		}
		return result;
	}

	/**
	 * @explain 分页条件查询用户   
	 * @param   param
	 * @return  JsonResult<IPage<T>>
	 * @author  戴艺辉
	 * @time    2019-06-11 09:49:42
	 */
    @RequestMapping(value = "/getSupplierPages",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：戴艺辉")
	public JsonResult<IPage<Map<String,Object>>> getSupplierPages(PageParam<SupplierData> param){
		JsonResult<IPage<Map<String,Object>>> returnPage=new JsonResult<IPage<Map<String,Object>>>();
		Page<SupplierData> page=new Page<SupplierData>(param.getPageNum(),param.getPageSize());
		QueryWrapper<SupplierData> queryWrapper =new QueryWrapper<SupplierData>();
		queryWrapper.setEntity(param.getParam());
		//分页数据
		IPage<Map<String,Object>> pageData=baseService.pageMaps(page, queryWrapper);
		returnPage.success(pageData);
		return returnPage;
	}
}