/**
 * @filename:SupplyPriceAdjustmentController 2019-06-11 09:49:43
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydsh.generator.common.JsonResult;
import com.ydsh.merchant.common.db.DBKeyGenerator;
import com.ydsh.merchant.common.enums.DBBusinessKeyTypeEnums;
import com.ydsh.merchant.common.enums.DBDictionaryEnumManager;
import com.ydsh.merchant.web.controller.base.AbstractController;
import com.ydsh.merchant.web.entity.SupplyPriceAdjustment;
import com.ydsh.merchant.web.entity.SupplyPriceAdjustmentDetail;
import com.ydsh.merchant.web.entity.dto.SupplyPriceAdjustmentDetailDto;
import com.ydsh.merchant.web.entity.dto.getSupplyPriceAdjustmentAndDetailsList;
import com.ydsh.merchant.web.entity.dto.reviewSupplyPriceAdjustment;
import com.ydsh.merchant.web.entity.dto.saveSupplyPriceAdjustmentAndDetails;
import com.ydsh.merchant.web.entity.dto.saveSupplyPriceAdjustmentAndDetailsTwo;
import com.ydsh.merchant.web.service.SupplyPriceAdjustmentDetailService;
import com.ydsh.merchant.web.service.SupplyPriceAdjustmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
	
	private static Timestamp now = new Timestamp(System.currentTimeMillis());
	
	@Autowired
	private SupplyPriceAdjustmentDetailService supplyPriceAdjustmentDetailService;
    /**
     * 
    * *供应价设置（采购管理-供应价设置）
    *
    * @param @param param
    * @param @return
    * @return
     */
	@RequestMapping(value = "/saveSupplyPriceAdjustmentAndDetails", method = RequestMethod.POST)
	@ApiOperation(value = "设置保存供应价", notes = "分页查询返回JsonResult<Object>,作者：戴艺辉")
	public JsonResult<Object> saveSupplyPriceAdjustmentAndDetails(@RequestBody saveSupplyPriceAdjustmentAndDetails param) {
		JsonResult<Object> returnPage = new JsonResult<Object>();
		// 验证是否为空
		//待审核
		param.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
		SupplyPriceAdjustment supplyPriceAdjustment =new SupplyPriceAdjustment();
        BeanUtils.copyProperties(param, supplyPriceAdjustment);		
        supplyPriceAdjustment.setCreateTime(now);
        supplyPriceAdjustment.setSupplyPriceAdjustmentNo(DBKeyGenerator.generatorDBKey(DBBusinessKeyTypeEnums.PPA, null));
		baseService.save(supplyPriceAdjustment);
		List<SupplyPriceAdjustmentDetailDto> supplyPriceAdjustmentDetailList =param.getSupplyPriceAdjustmentDetailList();
        for(SupplyPriceAdjustmentDetailDto var:supplyPriceAdjustmentDetailList) {
        	SupplyPriceAdjustmentDetail supplyPriceAdjustmentDetail=new SupplyPriceAdjustmentDetail();
        	var.setBppaId(supplyPriceAdjustment.getId());
        	BeanUtils.copyProperties(var, supplyPriceAdjustmentDetail);
        	supplyPriceAdjustmentDetail.setGcsOldPurchasePrice(new BigDecimal(var.getGcsOldPurchasePrice()).multiply(new BigDecimal("10000")).longValue());
        	supplyPriceAdjustmentDetail.setGcsNewPurchasePrice(new BigDecimal(var.getGcsNewPurchasePrice()).multiply(new BigDecimal("10000")).longValue());
        	supplyPriceAdjustmentDetailService.save(supplyPriceAdjustmentDetail);
        }
		returnPage.success("设置供应价成功！");
		return returnPage;
	}
	/**
	 * 
	 * *审核供应价设置（采购管理-供应价设置）
	 *
	 * @param @param param
	 * @param @return
	 * @return
	 */
	@RequestMapping(value = "/reviewSupplyPriceAdjustmentAndDetails", method = RequestMethod.POST)
	@ApiOperation(value = "审核供应价", notes = "分页查询返回JsonResult<Object>,作者：戴艺辉")
	public JsonResult<Object> reviewSupplyPriceAdjustmentAndDetails(@RequestBody reviewSupplyPriceAdjustment param) {
		JsonResult<Object> returnPage = new JsonResult<Object>();
		// 验证是否为空
		//待审核
		param.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
		SupplyPriceAdjustment supplyPriceAdjustment =new SupplyPriceAdjustment();
		supplyPriceAdjustment.setId(param.getId());
		supplyPriceAdjustment.setReviewStatus(param.getReviewStatus());
		supplyPriceAdjustment.setReviewTime(now);
		supplyPriceAdjustment.setReviewRemarks(param.getReviewRemarks());
		baseService.updateById(supplyPriceAdjustment);
		
		//审核通过，级联更新价格
//		if(param.getReviewStatus().equals(DBDictionaryEnumManager.review_1.getkey())) {
//			QueryWrapper<SupplyPriceAdjustmentDetail> queryWrapper = new QueryWrapper<SupplyPriceAdjustmentDetail>();
//			queryWrapper.eq("bppa_id", param.getId());
//			List<SupplyPriceAdjustmentDetail> list=supplyPriceAdjustmentDetailService.list(queryWrapper);
//			for(SupplyPriceAdjustmentDetail s:list) {
//				
//			}
//		}
		returnPage.success("审核供应价成功！");
		return returnPage;
	}
	
	
	/**
	 * 
	 * *得到供应价和明细数组
	 *
	 * @param @param param
	 * @param @return
	 * @return
	 */
	@RequestMapping(value = "/getSupplyPriceAdjustmentAndDetails", method = RequestMethod.POST)
	@ApiOperation(value = "得到供应价和明细数组", notes = "分页查询返回JsonResult<Object>,作者：戴艺辉")
	public JsonResult<List<saveSupplyPriceAdjustmentAndDetailsTwo>> getSupplyPriceAdjustmentAndDetails(@RequestBody getSupplyPriceAdjustmentAndDetailsList param) {
		JsonResult<List<saveSupplyPriceAdjustmentAndDetailsTwo>> returnPage = new JsonResult<List<saveSupplyPriceAdjustmentAndDetailsTwo>>();
		QueryWrapper<SupplyPriceAdjustment> queryWrapper = new QueryWrapper<SupplyPriceAdjustment>();
		List<saveSupplyPriceAdjustmentAndDetailsTwo> returnList=new ArrayList<saveSupplyPriceAdjustmentAndDetailsTwo>();
		queryWrapper.eq("id", param.getId());
		List<SupplyPriceAdjustment> supplyPriceAdjustmentList=baseService.list(queryWrapper);
		for(SupplyPriceAdjustment s:supplyPriceAdjustmentList) { // 100
			saveSupplyPriceAdjustmentAndDetailsTwo  entity =new saveSupplyPriceAdjustmentAndDetailsTwo();
			BeanUtils.copyProperties(s, entity);
			QueryWrapper<SupplyPriceAdjustmentDetail> queryWrapper1 = new QueryWrapper<SupplyPriceAdjustmentDetail>(); 
			queryWrapper1.eq("bppa_id", s.getId());
			List<SupplyPriceAdjustmentDetail> list2=supplyPriceAdjustmentDetailService.list(queryWrapper1);
			entity.setSupplyPriceAdjustmentDetailList(list2);
			returnList.add(entity);
		}
		returnPage.setData(returnList);
		//审核通过，级联更新价格
		return returnPage;
	}
	
}