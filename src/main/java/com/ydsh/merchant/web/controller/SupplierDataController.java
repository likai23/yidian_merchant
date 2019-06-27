/**
 * @filename:SupplierDataController 2019-06-11 09:49:43
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

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
import com.ydsh.merchant.common.exception.SystemException;
import com.ydsh.merchant.common.util.TextUtils;
import com.ydsh.merchant.web.controller.base.AbstractController;
import com.ydsh.merchant.web.entity.SupplierData;
import com.ydsh.merchant.web.entity.dto.LookAndTakeInSupplierDataDto;
import com.ydsh.merchant.web.entity.dto.SupplierDataDto;
import com.ydsh.merchant.web.entity.dto.deleteSupplierData;
import com.ydsh.merchant.web.entity.dto.reviewSupplierData;
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
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		// 供应商编码
		String sdNo = DBKeyGenerator.generatorDBKey(DBBusinessKeyTypeEnums.S, trade);
		JsonResult<Object> result = new JsonResult<Object>();
		if (null != entity) {
			entity.setSdNo(sdNo);
			boolean rsg = baseService.save(entity);
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
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		if (null != entity) {
			entity.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
			boolean rsg = baseService.updateById(entity);
			if (rsg) {
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
	 * @explain 启用/禁用供应商
	 * @param entity
	 * @return JsonResult
	 * @author 戴艺辉
	 * @time 2019-06-11 09:49:42
	 */
	@RequestMapping(value = "/updateSupplierDataStatus", method = RequestMethod.POST)
	@ApiOperation(value = "启用/禁用供应商", notes = "作者：戴艺辉")
	public JsonResult<Object> updateSupplierDataStatus(@RequestBody SupplierDataDto param) {
		JsonResult<Object> result = new JsonResult<Object>();
			String id = String.valueOf(param.getId());
			String supplierStatus = param.getSupplierStatus();
			if (TextUtils.isEmptys(id, supplierStatus)) {
				log.info("参数为空");
				throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
			}
			SupplierData supplierDataCheck = baseService.getById(Long.parseLong(id));
			if (supplierDataCheck == null) {
				log.info("参数异常");
				throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
			}
			// 启用
			if (supplierStatus.equals(DBDictionaryEnumManager.user_status_0.getkey())) {
				if (!(supplierDataCheck.getSupplierStatus().equals(DBDictionaryEnumManager.user_status_1.getkey()))) {
					log.info("不是禁用状态，不能启用");
					result.error("不是禁用状态，不能启用！");
					return result;
				}
				SupplierData supplierData = new SupplierData();
				supplierData.setId(Long.parseLong(id));
				supplierData.setSupplierStatus(supplierStatus);
				baseService.updateById(supplierData);
				result.success("更新状态成功！");
				return result;
			}
			// 禁用
			else if (supplierStatus.equals(DBDictionaryEnumManager.user_status_1.getkey())) {
				if (!(supplierDataCheck.getSupplierStatus().equals(DBDictionaryEnumManager.user_status_0.getkey()))) {
					log.info("不是启用状态");
					result.error("不是启用状态，不能禁用！");
					return result;
				}
				SupplierData supplierData = new SupplierData();
				supplierData.setId(Long.parseLong(id));
				supplierData.setSupplierStatus(supplierStatus);
				baseService.updateById(supplierData);
				result.success("更新状态成功！");
				return result;
			} else {
				log.info("参数异常");
				throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
			}
	}

	
	/**
	 * 
	* *删除供应商
	*
	* @param @param param
	* @param @return
	* @return
	 */
	@RequestMapping(value = "/deleteSupplierData", method = RequestMethod.POST)
	@ApiOperation(value = "删除供应商", notes = "作者：戴艺辉")
	public JsonResult<Object> deleteSupplierData(@RequestBody deleteSupplierData param) {
		JsonResult<Object> result = new JsonResult<Object>();
		if(param.getId()==null||param.getStatus()==null||param.getStatus()=="") {
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		String id = String.valueOf(param.getId());
		SupplierData supplierData = new SupplierData();
		supplierData.setId(Long.parseLong(id));
		supplierData.setStatus(DBDictionaryEnumManager.invalid.getkey());
		baseService.updateById(supplierData);
		result.success("删除成功！");
		return result;
	}
   /**
	* 
	* *审核供应商
	*
	* @param @param param
	* @param @return
	* @return
	 */
	@RequestMapping(value = "/reviewSupplierData", method = RequestMethod.POST)
	@ApiOperation(value = "审核供应商", notes = "作者：戴艺辉")
	public JsonResult<Object> reviewSupplierData(@RequestBody reviewSupplierData param) {
		JsonResult<Object> result = new JsonResult<Object>();
		String id = String.valueOf(param.getId());
		String reviewStatus = param.getReviewStatus();
		String reviewRemarks = param.getReviewRemarks();
		if (TextUtils.isEmptys(id, reviewStatus)) {
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		SupplierData supplierData = new SupplierData();
		supplierData.setId(Long.parseLong(id));
		supplierData.setReviewStatus(reviewStatus);
		supplierData.setReviewRemarks(reviewRemarks);
		baseService.updateById(supplierData);
		result.success("审核成功！");
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
	@RequestMapping(value = "/getSupplierById", method = RequestMethod.GET)
	@ApiOperation(value = "获取供应商基本信息(查看或修改进入时)", notes = "作者：戴艺辉")
	public JsonResult<SupplierData> getSupplierById(@RequestBody LookAndTakeInSupplierDataDto param) {
		JsonResult<SupplierData> result = new JsonResult<SupplierData>();
		String id = String.valueOf(param.getId());
		String getSign = param.getGetSign();
		if (TextUtils.isEmptys(id, getSign)) {
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		// 查看
		if (getSign.equals("lookSupplier")) {
			SupplierData supplierData = baseService.getById(id);
			result.setMessage("查询成功！");
			result.setData(supplierData);
			return result;
		}
		// 修改时进入查看
		else if (getSign.equals("lookSupplierWithStatus")) {
			SupplierData supplierData = baseService.getById(id);
			//只有待审核和审核不通过才能修改
			if ((supplierData.getReviewStatus()).equals(DBDictionaryEnumManager.review_0.getkey())
					|| (supplierData.getReviewStatus()).equals(DBDictionaryEnumManager.review_2.getkey())) {
				result.setMessage("查询成功！");
				result.setData(supplierData);
				return result;
			}else {
				result.error("状态不为待审核或审核不通过，不允许修改！");
				return result;
			}
		}else {
			log.info("参数异常");
			throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
		}
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
	public JsonResult<IPage<SupplierData>> getSupplierPages(PageParam<SupplierData> param){
		JsonResult<IPage<SupplierData>> returnPage=new JsonResult<IPage<SupplierData>>();
		Page<SupplierData> page=new Page<SupplierData>(param.getPageNum(),param.getPageSize());
		if(param.getPageSize()>500) {
			log.error("分页最大限制500，" +param);
			returnPage.error("分页最大限制500");
			return returnPage;
		}
		QueryWrapper<SupplierData> queryWrapper =new QueryWrapper<SupplierData>();
		queryWrapper.setEntity(param.getParam());
		//分页数据
		IPage<SupplierData> pageData=baseService.page(page, queryWrapper);
		returnPage.success(pageData);
		return returnPage;
	}
}