/**
 * @filename:CustomerDataController 2019-06-11 09:49:42
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ydsh.merchant.web.entity.CustomerData;
import com.ydsh.merchant.web.entity.dto.CustomerDataDto;
import com.ydsh.merchant.web.entity.dto.LookAndTakeInCustomerDataDto;
import com.ydsh.merchant.web.entity.dto.deleteCustomerData;
import com.ydsh.merchant.web.entity.dto.reviewCustomerData;
import com.ydsh.merchant.web.entity.dto.updateCustomerData;
import com.ydsh.merchant.web.service.CustomerDataService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 自定义方法写在这里
 * </p>
 * 
 * <p>
 * 说明： 客户基本资料表API接口层
 * </P>
 * 
 * @version: V1.0
 * @author: 戴艺辉
 *
 */
@Api(description = "客户基本资料表", value = "客户基本资料表")
@RestController
@RequestMapping("/customerData")
@Slf4j
public class CustomerDataController extends AbstractController<CustomerDataService, CustomerData> {
	private static Timestamp now = new Timestamp(System.currentTimeMillis());


	@Autowired
	private CustomerDataService customerDataService;

	/**
	 * @explain 添加
	 * @param entity
	 * @return JsonResult
	 * @author 戴艺辉
	 * @time 2019-06-11 09:49:42
	 */
	@RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
	@ApiOperation(value = "添加", notes = "作者：戴艺辉")
	public JsonResult<Object> saveCustomer(@RequestBody CustomerData entity) {
		String customerName = entity.getCustomerName();
		String customerCategory = entity.getCustomerCategory();
		String customerType = entity.getCustomerType();
		String customerPlatformIds = entity.getCustomerPlatformIds();
		String customerLegperson = entity.getCustomerLegperson();
		String customerDockperson = entity.getCustomerDockperson();
		String customerProvince = entity.getCustomerProvince();
		String customerCity = entity.getCustomerCity();
		String customerDockphone = entity.getCustomerDockphone();
		String salesManId = entity.getSalesManId();
		String saleMain = entity.getSaleMain();
		String phonechangeIf = entity.getPhonechangeIf();
		String registerNumber = entity.getRegisterNumber();
		String customerPersonType = entity.getCustomerPersonType();
		String businessFile = entity.getBusinessFile();
		String cuspersonId = entity.getCuspersonId();
		String idcardOn = entity.getIdcardOn();
		String idcardUnder = entity.getIdcardUnder();
		// 供应商编码
		String cdNo = DBKeyGenerator.generatorDBKey(DBBusinessKeyTypeEnums.C, customerCategory);
		if (TextUtils.isEmptys(customerName, customerCategory, customerType, customerPlatformIds, customerLegperson,
				customerDockperson, customerProvince, customerCity, customerDockphone, salesManId, saleMain,
				phonechangeIf, registerNumber, customerPersonType, businessFile, cuspersonId, idcardOn, idcardUnder)) {
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		JsonResult<Object> result = new JsonResult<Object>();
		if (null != entity) {
			entity.setCdNo(cdNo);
			// 未审核状态
			entity.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
			entity.setSubmitTime(now);
			boolean rsg = baseService.save(entity);
			if (rsg) {
				result.success("添加成功");
			} else {
				result.error("添加失败！");
			}
		} else
			result.error("请传入正确参数！");
		return result;
	}

	/**
	 * 
	 * * 更新客户基本信息
	 *
	 * @param @param  param
	 * @param @return
	 * @return
	 */
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	@ApiOperation(value = "修改客户", notes = "作者：戴艺辉")
	public JsonResult<Object> updateCustomer(@RequestBody CustomerDataDto param) {
		JsonResult<Object> result = new JsonResult<Object>();
		String id = String.valueOf(param.getId());
		String cdNo = param.getCdNo();
		String customerName = param.getCustomerName();
		String customerCategory = param.getCustomerCategory();
		String customerType = param.getCustomerType();
		String customerPlatformIds = param.getCustomerPlatformIds();
		String customerLegperson = param.getCustomerLegperson();
		String customerDockperson = param.getCustomerDockperson();
		String customerProvince = param.getCustomerProvince();
		String customerCity = param.getCustomerCity();
		String customerDockphone = param.getCustomerDockphone();
		String salesManId = param.getSalesManId();
		String saleMain = param.getSaleMain();
		String phonechangeIf = param.getPhonechangeIf();
		String registerNumber = param.getRegisterNumber();
		String customerPersonType = param.getCustomerPersonType();
		String businessFile = param.getBusinessFile();
		String cuspersonId = param.getCuspersonId();
		String idcardOn = param.getIdcardOn();
		String idcardUnder = param.getIdcardUnder();
		// 供应商编码
		if (TextUtils.isEmptys(id, cdNo, customerName, customerCategory, customerType, customerPlatformIds,
				customerLegperson, customerDockperson, customerProvince, customerCity, customerDockphone, salesManId,
				saleMain, phonechangeIf, registerNumber, customerPersonType, businessFile, cuspersonId, idcardOn,
				idcardUnder)) {
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}

		CustomerData customerDataCheck = baseService.getById(id);
		if (customerDataCheck == null) {
			log.info("参数异常");
			throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
		}
		String reviewStatus = customerDataCheck.getReviewStatus();
		String review_0 = DBDictionaryEnumManager.review_0.getkey();
		String review_2 = DBDictionaryEnumManager.review_2.getkey();
		if (!(review_0.equals(reviewStatus) || review_2.equals(reviewStatus))) {
			log.info("不是未审核或审核不通过状态，不允许修改！");
			result.error("不是未审核或审核不通过状态！");
			return result;
		}
		CustomerData customerData = new CustomerData();
		customerData.setId(param.getId());
		customerData.setCustomerForshort(param.getCustomerForshort());
		customerData.setCustomerLogo(param.getCustomerLogo());
		customerData.setCustomerAddress(param.getCustomerAddress());
		customerData.setChainIf(param.getChainIf());
		customerData.setCustomernsNum(param.getCustomernsNum());
		customerData.setCustomernsAddress(param.getCustomernsAddress());
		customerData.setCustomerPhone(param.getCustomerPhone());
		customerData.setBankOpen(param.getBankOpen());
		customerData.setCustomerStatus(param.getCustomerStatus());
		if (param.getBankId() != null) {
			customerData.setBankId(param.getBankId());
		}
		customerData.setEmail(param.getEmail());
		customerData.setH5Address(param.getH5Address());
		customerData.setChannelNum(param.getChannelNum());
		customerData.setOpenDid(param.getOpenDid());
		customerData.setMd5Password(param.getMd5Password());
		customerData.setDesPassword(param.getDesPassword());
		if (param.getPlatformUseAmount() != null) {
			customerData.setPlatformUseAmount(param.getPlatformUseAmount());
		}
		if (param.getPlatformUse() != null) {
			customerData.setPlatformUse(param.getPlatformUse());
		}
		// 提交人id
//			customerData.setUpdateId();
		customerData.setUpdateTime(now);
		customerData.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
		baseService.updateById(customerData);
		result.setMessage("更新客户信息成功，待审核通过生效！");
		return result;
	}

	/**
	 * 
	 * *启用/禁用 客户
	 * 
	 * @param @param  param
	 * @param @return
	 * @return
	 */
	@RequestMapping(value = "/updateCustomerStatus", method = RequestMethod.POST)
	@ApiOperation(value = "修改客户状态", notes = "作者：戴艺辉")
	public JsonResult<Object> updateCustomerStatus(@RequestBody updateCustomerData param) {
		JsonResult<Object> result = new JsonResult<Object>();
		// 更新客户状态
		String id = String.valueOf(param.getId());
		String status = param.getCustomerStatus();
		if (TextUtils.isEmptys(id, status)) {
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		CustomerData customerDataCheck = baseService.getById(Long.parseLong(id));
		if (customerDataCheck == null) {
			log.info("参数异常");
			throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
		}
		// 启用
		if (status.equals(DBDictionaryEnumManager.user_status_0.getkey())) {
			if (!(customerDataCheck.getCustomerStatus().equals(DBDictionaryEnumManager.user_status_1.getkey()))) {
				log.info("不是禁用状态，不能启用");
				result.error("不是禁用状态，不能启用！");
				return result;
			}
			CustomerData customerData = new CustomerData();
			customerData.setId(Long.parseLong(id));
			customerData.setCustomerStatus(status);
			baseService.updateById(customerData);
			result.setMessage("更新状态成功！");
		}
		// 禁用
		else if (status.equals(DBDictionaryEnumManager.user_status_1.getkey())) {
			if (!(customerDataCheck.getCustomerStatus().equals(DBDictionaryEnumManager.user_status_0.getkey()))) {
				log.info("不是启用状态");
				result.error("不是启用状态，不能禁用！");
				return result;
			}
			CustomerData customerData = new CustomerData();
			customerData.setId(Long.parseLong(id));
			customerData.setCustomerStatus(status);
			baseService.updateById(customerData);
			result.setMessage("更新状态成功！");
		}
		// 加入黑名单
		else if (status.equals(DBDictionaryEnumManager.user_status_2.getkey())) {
			CustomerData customerData = new CustomerData();
			customerData.setId(Long.parseLong(id));
			customerData.setCustomerStatus(status);
			baseService.updateById(customerData);
			result.setMessage("更新状态成功！");
		} else {
			log.info("参数异常");
			throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
		}
		return result;
	}

	/**
	 * 
	 * *审核 客户
	 * 
	 * @param @param  param
	 * @param @return
	 * @return
	 */
	@RequestMapping(value = "/reviewCustomerStatus", method = RequestMethod.POST)
	@ApiOperation(value = "审核客户", notes = "作者：戴艺辉")
	public JsonResult<Object> reviewCustomerStatus(@RequestBody reviewCustomerData param) {
		JsonResult<Object> result = new JsonResult<Object>();
		String id = String.valueOf(param.getId());
		String reviewStatus = param.getReviewStatus();
		String reviewBz = param.getReviewRemarks();
		if (TextUtils.isEmptys(id, reviewStatus, reviewBz)) {
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		CustomerData customerDataCheck = baseService.getById(Long.parseLong(id));
		if (customerDataCheck == null) {
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		if (!(customerDataCheck.getReviewStatus().equals(DBDictionaryEnumManager.review_0.getkey()))) {
			log.info("不是待审核状态，不可审核！");
			result.error("不是待审核状态，不可审核！");
			return result;
		} else {
			CustomerData customerData = new CustomerData();
			customerData.setId(param.getId());
			customerData.setReviewStatus(param.getReviewStatus());
			customerData.setReviewRemarks(reviewBz);
			baseService.updateById(customerData);
			result.success("审核成功！");
			return result;
		}
	}

	/**
	 * 
	 * *删除客户
	 *
	 * @param @param  param
	 * @param @return
	 * @return
	 */
	@RequestMapping(value = "/deleteCustomerData", method = RequestMethod.POST)
	@ApiOperation(value = "删除客户", notes = "作者：戴艺辉")
	public JsonResult<Object> deleteCustomerData(@RequestBody deleteCustomerData param) {
		JsonResult<Object> result = new JsonResult<Object>();
		if (param.getId() == null) {
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		String id = String.valueOf(param.getId());
		CustomerData customerData = new CustomerData();

		customerData.setId(Long.parseLong(id));
		customerData.setStatus(Integer.parseInt(DBDictionaryEnumManager.invalid.getkey()));
		baseService.updateById(customerData);
		result.setMessage("删除成功！");
		return result;
	}

	/**
	 * @explain 分页条件查询用户
	 * @param param
	 * @return JsonResult<IPage<T>>
	 * @author 戴艺辉
	 * @time 2019-06-11 09:49:42
	 */
	@RequestMapping(value = "/selectUserPages", method = RequestMethod.GET)
	@ApiOperation(value = "分页查询", notes = "分页查询返回JsonResult<IPage<Map<String, Object>>>,作者：戴艺辉")
	public JsonResult<IPage<CustomerData>> selectUserPages(PageParam<CustomerData> param) {
		JsonResult<IPage<CustomerData>> returnPage = new JsonResult<IPage<CustomerData>>();
		Page<CustomerData> page = new Page<CustomerData>(param.getPageNum(), param.getPageSize());
		if (param.getPageSize() > 500) {
			log.error("分页最大限制500，" + param);
			returnPage.error("分页最大限制500");
			return returnPage;
		}
		QueryWrapper<CustomerData> queryWrapper = new QueryWrapper<CustomerData>();
		queryWrapper.setEntity(param.getParam());
		// 分页数据
		IPage<CustomerData> pageData = customerDataService.page(page, queryWrapper);
		returnPage.success(pageData);
		return returnPage;
	}

	/**
	 * 
	 * *根据id获取客户基本信息
	 *
	 * @param @param  param
	 * @param @return
	 * @return
	 */
	@RequestMapping(value = "/getCustomerById", method = RequestMethod.GET)
	@ApiOperation(value = "分页查询", notes = "分页查询返回JsonResult<CustomerData>,作者：戴艺辉")
	public JsonResult<CustomerData> getCustomerById(@RequestBody LookAndTakeInCustomerDataDto param) {
		JsonResult<CustomerData> returnPage = new JsonResult<CustomerData>();
		String id = String.valueOf(param.getId());
		String getSign = param.getGetSign();
		if (TextUtils.isEmptys(id, getSign)) {
			log.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		// 查看
		if (getSign.equals("lookCustomer")) {
			CustomerData customerData = customerDataService.getById(id);
			if (customerData == null) {
				log.info("参数异常");
				throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
			}
			returnPage.success("查询成功！");
			returnPage.setData(customerData);
		}
		// 修改进入查看
		else if (getSign.equals("lookCustomerWithStatus")) {
			CustomerData customerData = customerDataService.getById(id);
			if (customerData == null) {
				log.info("参数异常");
				throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
			}
			String reviewStatus = customerData.getReviewStatus();
			// 审核状态为0或者不通过才可以查看
			if (reviewStatus.equals(DBDictionaryEnumManager.review_0.getkey())
					|| reviewStatus.equals(DBDictionaryEnumManager.review_2.getkey())) {
				returnPage.success("查询成功！");
				returnPage.setData(customerData);
			} else {
				returnPage.success("审核状态不对，查询失败！");
				returnPage.setData(null);
			}
		} else {
			log.info("参数异常");
			throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
		}
		return returnPage;
	}

}