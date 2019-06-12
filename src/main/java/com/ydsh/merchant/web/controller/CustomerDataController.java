/**
 * @filename:CustomerDataController 2019-06-11 09:49:42
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ydsh.merchant.common.enums.SuccessCode;
import com.ydsh.merchant.common.exception.SystemException;
import com.ydsh.merchant.common.util.TextUtils;
import com.ydsh.merchant.dto.CustomerDataAdapters;
import com.ydsh.merchant.web.controller.base.AbstractController;
import com.ydsh.merchant.web.entity.CustomerData;
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

	private static Logger logger = LoggerFactory.getLogger(CustomerDataController.class);

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
			logger.info("参数为空");
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
				result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
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
	 * 1-修改客户基本信息 2-修改客户状态 3-审核客户状态
	 * 
	 * @param @param  param
	 * @param @return
	 * @return
	 */
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	@ApiOperation(value = "修改客户", notes = "作者：戴艺辉")
	public JsonResult<Object> updateCustomer(@RequestBody Map<String, Object> param) {
		JsonResult<Object> result = new JsonResult<Object>();
		String updateSign = TextUtils.getMapForKeyToString(param, "updateSign");
		if ("".equals(updateSign) || updateSign == null) {
			logger.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		} else {
			// 更新客户基本信息
			if (updateSign.equals("updateCustomer")) {
				String id = TextUtils.getMapForKeyToString(param, "id");
				String cdNo = TextUtils.getMapForKeyToString(param, "cdNo");
				String customerName = TextUtils.getMapForKeyToString(param, "customerName");
				String customerCategory = TextUtils.getMapForKeyToString(param, "customerCategory");
				String customerType = TextUtils.getMapForKeyToString(param, "customerType");
				String customerPlatformIds = TextUtils.getMapForKeyToString(param, "customerPlatformIds");
				String customerLegperson = TextUtils.getMapForKeyToString(param, "customerLegperson");
				String customerDockperson = TextUtils.getMapForKeyToString(param, "customerDockperson");
				String customerProvince = TextUtils.getMapForKeyToString(param, "customerProvince");
				String customerCity = TextUtils.getMapForKeyToString(param, "customerCity");
				String customerDockphone = TextUtils.getMapForKeyToString(param, "customerDockphone");
				String salesManId = TextUtils.getMapForKeyToString(param, "salesManId");
				String saleMain = TextUtils.getMapForKeyToString(param, "saleMain");
				String phonechangeIf = TextUtils.getMapForKeyToString(param, "phonechangeIf");
				String registerNumber = TextUtils.getMapForKeyToString(param, "registerNumber");
				String customerPersonType = TextUtils.getMapForKeyToString(param, "customerPersonType");
				String businessFile = TextUtils.getMapForKeyToString(param, "businessFile");
				String cuspersonId = TextUtils.getMapForKeyToString(param, "cuspersonId");
				String idcardOn = TextUtils.getMapForKeyToString(param, "idcardOn");
				String idcardUnder = TextUtils.getMapForKeyToString(param, "idcardUnder");
				// 供应商编码
				if (TextUtils.isEmptys(id, cdNo, customerName, customerCategory, customerType, customerPlatformIds,
						customerLegperson, customerDockperson, customerProvince, customerCity, customerDockphone,
						salesManId, saleMain, phonechangeIf, registerNumber, customerPersonType, businessFile,
						cuspersonId, idcardOn, idcardUnder)) {
					logger.info("参数为空");
					throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
				}

				CustomerData customerDataCheck = baseService.getById(id);
				if (customerDataCheck == null) {
					logger.info("参数异常");
					throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
				}
				String reviewStaatus = customerDataCheck.getReviewStatus();
				String review_0 = DBDictionaryEnumManager.review_0.getkey();
				String review_2 = DBDictionaryEnumManager.review_2.getkey();
				if (!(review_0.equals(reviewStaatus) || review_2.equals(reviewStaatus))) {
					logger.info("不是未审核或审核不通过状态，不允许修改！");
					throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "不是未审核或审核不通过状态！", new Exception());
				}
				CustomerData customerData = new CustomerData();
				customerData.setId(Long.parseLong(id));
				customerData.setCustomerName(customerName);
				customerData.setCustomerCategory(customerCategory);
				customerData.setCustomerType(customerType);
				customerData.setCustomerPlatformIds(customerPlatformIds);
				customerData.setCustomerLegperson(customerLegperson);
				customerData.setCustomerDockperson(customerDockperson);
				customerData.setCustomerProvince(customerProvince);
				customerData.setCustomerCity(customerCity);
				customerData.setCustomerDockphone(customerDockphone);
				customerData.setSalesManId(salesManId);
				customerData.setSaleMain(saleMain);
				customerData.setPhonechangeIf(phonechangeIf);
				customerData.setRegisterNumber(registerNumber);
				customerData.setCustomerPersonType(customerPersonType);
				customerData.setBusinessFile(businessFile);
				customerData.setCuspersonId(cuspersonId);
				customerData.setIdcardOn(idcardOn);
				customerData.setIdcardUnder(idcardUnder);
				customerData.setCustomerForshort(TextUtils.getMapForKeyToString(param, "customerForshort"));
				customerData.setCustomerLogo(TextUtils.getMapForKeyToString(param, "customerLogo"));
				customerData.setCustomerAddress(TextUtils.getMapForKeyToString(param, "customerAddress"));
				customerData.setChainIf(TextUtils.getMapForKeyToString(param, "chainIf"));
				customerData.setCustomernsNum(TextUtils.getMapForKeyToString(param, "customernsNum"));
				customerData.setCustomernsAddress(TextUtils.getMapForKeyToString(param, "customernsAddress"));
				customerData.setCustomerPhone(TextUtils.getMapForKeyToString(param, "customerPhone"));
				customerData.setBankOpen(TextUtils.getMapForKeyToString(param, "bankOpen"));
				customerData.setCustomerStatus(TextUtils.getMapForKeyToString(param, "customerStatus"));
				String bankId = TextUtils.getMapForKeyToString(param, "bankId");
				if (bankId != null) {
					customerData.setBankId(Long.parseLong(bankId));
				}
				customerData.setEmail(TextUtils.getMapForKeyToString(param, "email"));
				customerData.setH5Address(TextUtils.getMapForKeyToString(param, "h5Address"));
				customerData.setChannelNum(TextUtils.getMapForKeyToString(param, "channelNum"));
				customerData.setOpenDid(TextUtils.getMapForKeyToString(param, "openDid"));
				customerData.setMd5Password(TextUtils.getMapForKeyToString(param, "md5Password"));
				customerData.setDesPassword(TextUtils.getMapForKeyToString(param, "desPassword"));
				String platformUseAmount = TextUtils.getMapForKeyToString(param, "platformUseAmount");
				if (platformUseAmount != null) {
					customerData.setPlatformUseAmount(Long.parseLong(platformUseAmount));
				}
				String platformUse = TextUtils.getMapForKeyToString(param, "platformUse");
				if (platformUse != null) {
					customerData.setPlatformUse(Integer.parseInt(platformUse));
				}
				// 提交人id
// 				customerData.setUpdateId();
				customerData.setUpdateTime(now);
				customerData.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
				baseService.updateById(customerData);
				result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
				result.setMessage("更新客户信息成功，待审核通过生效！");
			}
			// 更新客户状态
			else if (updateSign.equals("updateCustomerStatus")) {
				String id = TextUtils.getMapForKeyToString(param, "id");
				String status = TextUtils.getMapForKeyToString(param, "status");
				if (TextUtils.isEmptys(id, status)) {
					logger.info("参数为空");
					throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
				}
				CustomerData customerDataCheck = baseService.getById(Long.parseLong(id));
				if (customerDataCheck == null) {
					logger.info("参数异常");
					throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
				}
				// 启用
				if (status.equals(DBDictionaryEnumManager.user_status_0.getkey())) {
					if (!(customerDataCheck.getCustomerStatus()
							.equals(DBDictionaryEnumManager.user_status_1.getkey()))) {
						logger.info("不是禁用状态，不能启用");
						throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "不是禁用状态，不能启用", new Exception());
					}
					CustomerData customerData = new CustomerData();
					customerData.setId(Long.parseLong(id));
					customerData.setCustomerStatus(status);
					baseService.updateById(customerData);
					result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
					result.setMessage("更新状态成功！");
				}
				// 禁用
				else if (status.equals(DBDictionaryEnumManager.user_status_1.getkey())) {
					if (!(customerDataCheck.getCustomerStatus()
							.equals(DBDictionaryEnumManager.user_status_0.getkey()))) {
						logger.info("不是启用状态");
						throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "不是启用状态，不能禁用", new Exception());
					}
					CustomerData customerData = new CustomerData();
					customerData.setId(Long.parseLong(id));
					customerData.setCustomerStatus(status);
					baseService.updateById(customerData);
					result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
					result.setMessage("更新状态成功！");
				}
				// 加入黑名单
				else if (status.equals(DBDictionaryEnumManager.user_status_2.getkey())) {
					CustomerData customerData = new CustomerData();
					customerData.setId(Long.parseLong(id));
					customerData.setCustomerStatus(status);
					baseService.updateById(customerData);
					result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
					result.setMessage("更新状态成功！");
				} else {
					logger.info("参数异常");
					throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
				}
			}
			// 审核客户状态,级联更新客户账户
			else if (updateSign.equals("reviewCustomer")) {
				String id = TextUtils.getMapForKeyToString(param, "id");
				String reviewStatus = TextUtils.getMapForKeyToString(param, "reviewStatus");
				String reviewBz = TextUtils.getMapForKeyToString(param, "reviewBz");
				if (TextUtils.isEmptys(id, reviewStatus, reviewBz)) {
					logger.info("参数为空");
					throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
				}
				CustomerData customerDataCheck = baseService.getById(Long.parseLong(id));
				if (customerDataCheck == null) {
					logger.info("参数为空");
					throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
				} else if (!(customerDataCheck.getReviewStatus().equals(DBDictionaryEnumManager.review_0.getkey()))) {
					logger.info("不是待审核状态，不可审核！");
					throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "不是待审核状态，不可审核！", new Exception());
				}
			}
			// 删除客户
			else if (updateSign.equals("deleteCustomer")) {
				String id = TextUtils.getMapForKeyToString(param, "id");
				CustomerData customerData = new CustomerData();
				customerData.setId(Long.parseLong(id));
				customerData.setStatus(Integer.parseInt(DBDictionaryEnumManager.invalid.getkey()));
				baseService.updateById(customerData);
				result.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
				result.setMessage("删除成功！");
			} else {
				logger.info("参数异常");
				throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
			}
		}
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
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<Map<String,Object>>],作者：戴艺辉")
	public JsonResult<IPage<Map<String, Object>>> selectUserPages(PageParam<CustomerData> param) {
		JsonResult<IPage<Map<String, Object>>> returnPage = new JsonResult<IPage<Map<String, Object>>>();
		Page<CustomerData> page = new Page<CustomerData>(param.getPageNum(), param.getPageSize());
		QueryWrapper<CustomerData> queryWrapper = new QueryWrapper<CustomerData>();
		queryWrapper.setEntity(param.getParam());
		// 分页数据
		IPage<Map<String, Object>> pageData = customerDataService.pageMaps(page, queryWrapper);
//		List<Map<String,Object>> list=CustomerDataAdapters.convertSystemBaseConfigToList(pageData.getRecords());
//		pageData.getRecords().clear();
//		pageData.setRecords(list);
		returnPage.success(pageData);
		return returnPage;
	}

	/**
	 * 
	 * 根据id获取客户基本信息
	 *
	 * @param @param  param
	 * @param @return
	 * @return
	 */
	@RequestMapping(value = "/getCustomerById", method = RequestMethod.GET)
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<CustomerData>],作者：戴艺辉")
	public JsonResult<CustomerData> getCustomerById(@RequestBody Map<String, Object> param) {
		JsonResult<CustomerData> returnPage = new JsonResult<CustomerData>();
		String id = TextUtils.getMapForKeyToString(param, "id");
		String getSign = TextUtils.getMapForKeyToString(param, "getSign");
		if (TextUtils.isEmptys(id, getSign)) {
			logger.info("参数为空");
			throw new SystemException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空", new Exception());
		}
		// 查看
		if (getSign.equals("lookCustomer")) {
			CustomerData customerData = customerDataService.getById(id);
			if (customerData == null) {
				logger.info("参数异常");
				throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
			}
			returnPage.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
			returnPage.setMessage("查询成功！");
			returnPage.setData(customerData);
		}
		// 修改进入查看
		else if (getSign.equals("lookCustomerWithStatus")) {
			CustomerData customerData = customerDataService.getById(id);
			if (customerData == null) {
				logger.info("参数异常");
				throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
			}
			String reviewStatus = customerData.getReviewStatus();
			//审核状态为0或者不通过才可以查看
			if (reviewStatus.equals(DBDictionaryEnumManager.review_0.getkey())
					|| reviewStatus.equals(DBDictionaryEnumManager.review_2.getkey())) {
				returnPage.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
				returnPage.setMessage("查询成功！");
				returnPage.setData(customerData);
			} else {
				returnPage.setCode(String.valueOf(SuccessCode.SYS_SUCCESS.getCode()));
				returnPage.setMessage("审核状态不对，查询失败！");
				returnPage.setData(null);
			}
		} else {
			logger.info("参数异常");
			throw new SystemException(ErrorCode.SYS_EXCEPTION.getCode(), "参数异常", new Exception());
		}

		return returnPage;
	}
}