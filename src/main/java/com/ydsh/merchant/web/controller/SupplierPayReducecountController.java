/**
 * @filename:SupplierPayReducecountController 2019-06-19 10:47:08
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
import com.ydsh.merchant.web.entity.SupplierPayReducecount;
import com.ydsh.merchant.web.entity.dto.ReviewSupplierPayReducecountDto;
import com.ydsh.merchant.web.entity.dto.SupplierPayReducecountAndDataDto;
import com.ydsh.merchant.web.entity.dto.SupplierPayReducecountDto;
import com.ydsh.merchant.web.entity.dto.SupplierPayReducecountQueryDto;
import com.ydsh.merchant.web.service.SupplierPayReducecountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
/**   
 * <p>自定义方法写在这里</p>
 * 
 * <p>说明： 供应商退款记录表API接口层</P>
 * @version: V1.0
 * @author: 戴艺辉
 *
 */
@Api(description = "供应商退款记录表",value="供应商退款记录表" )
@RestController
@RequestMapping("/supplierPayReducecount")
@Slf4j
public class SupplierPayReducecountController extends AbstractController<SupplierPayReducecountService,SupplierPayReducecount>{
	
	private static Timestamp now = new Timestamp(System.currentTimeMillis());

	
	
	/**
	 * @explain 分页条件查询用户   
	 * @param   param
	 * @return  JsonResult<IPage<T>>
	 * @author  戴艺辉
	 * @time    2019-06-11 09:49:42
	 */
    @RequestMapping(value = "/selectSupplierPayReducecountPages",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：戴艺辉")
	public JsonResult<IPage<SupplierPayReducecountAndDataDto>> selectSupplierPayReducecountPages(PageParam<SupplierPayReducecountQueryDto> param){
		JsonResult<IPage<SupplierPayReducecountAndDataDto>> returnPage=new JsonResult<IPage<SupplierPayReducecountAndDataDto>>();
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(param.getPageNum(),param.getPageSize());
		QueryWrapper<SupplierPayReducecountQueryDto> queryWrapper =new QueryWrapper<SupplierPayReducecountQueryDto>();
		queryWrapper.setEntity(param.getParam());
		if(param.getParam()==null) {
			queryWrapper.setEntity(new SupplierPayReducecountQueryDto());
		}
		//分页数据
		IPage<SupplierPayReducecountAndDataDto> pageData=baseService.selectPayReducePage(page, queryWrapper.getEntity());
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
	@RequestMapping(value = "/saveSupplierPayReducecount", method = RequestMethod.POST)
	@ApiOperation(value = "添加退款记录", notes = "作者：戴艺辉")
	public JsonResult<Object> saveSupplierPayReducecount(@RequestBody SupplierPayReducecountDto entity) {
		JsonResult<Object> result = new JsonResult<Object>();
		if (null != entity) {
			String refoundAmount=entity.getRefundAmount() ;
			String refoundWay=entity.getRefundWay() ;
			String file=entity.getFile() ;
			if (TextUtils.isEmptys(refoundAmount,refoundWay,file)) {
				log.info("参数为空");
				throw new BizException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空");
			}
			SupplierPayReducecount supplierPayReducecount = new SupplierPayReducecount();
			BeanUtils.copyProperties(entity, supplierPayReducecount);
			supplierPayReducecount.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
			supplierPayReducecount
					.setRefundAmount(new BigDecimal(entity.getRefundAmount()).multiply(new BigDecimal("10000")).longValue());
			boolean rsg = baseService.save(supplierPayReducecount);
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
	@RequestMapping(value = "/reviewSupplierPayReducecount", method = RequestMethod.GET)
	@ApiOperation(value = "审核充值记录", notes = "作者：戴艺辉")
	public JsonResult<Object> reviewSupplierPayReducecount(@RequestBody ReviewSupplierPayReducecountDto entity) {
		JsonResult<Object> result = new JsonResult<Object>();
		String id=entity.getId();
		String reviewStatus=entity.getReviewStatus();
		if (TextUtils.isEmptys(id, reviewStatus)) {
			log.info("参数为空");
			throw new BizException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空");
		}
		SupplierPayReducecount supplierPayReducecount = baseService.getById(id);
		// 待审核状态才允许审核
		if ((supplierPayReducecount.getReviewStatus()).equals(DBDictionaryEnumManager.review_0.getkey())) {
			supplierPayReducecount.setReviewTime(now);
			supplierPayReducecount.setReviewStatus(reviewStatus);
			baseService.updateById(supplierPayReducecount);
			result.success("审核成功！");
			return result;
		} else {
			log.info("不是待审核状态不允许审核！");
			result.error("不是待审核状态不允许审核！");
			return result;
		}
	}
}