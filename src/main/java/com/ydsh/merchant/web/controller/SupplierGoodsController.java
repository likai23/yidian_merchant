/**
 * @filename:SupplierGoodsController 2019-06-11 09:49:43
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2020 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
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
import com.ydsh.merchant.common.enums.ErrorCode;
import com.ydsh.merchant.common.exception.BizException;
import com.ydsh.merchant.common.util.TextUtils;
import com.ydsh.merchant.web.controller.base.AbstractController;
import com.ydsh.merchant.web.entity.SupplierGoods;
import com.ydsh.merchant.web.entity.dto.SupplierGoodsAndDataDto;
import com.ydsh.merchant.web.entity.dto.SupplierGoodsChangeDto;
import com.ydsh.merchant.web.entity.dto.SupplierGoodsDto;
import com.ydsh.merchant.web.service.SupplierGoodsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
/**   
 * <p>自定义方法写在这里</p>
 * 
 * <p>说明： 供应商和商品的关系API接口层</P>
 * @version: V1.0
 * @author: 戴艺辉
 *
 */
@Api(description = "供应商和商品的关系",value="供应商和商品的关系" )
@RestController
@RequestMapping("/supplierGoods")
@Slf4j
public class SupplierGoodsController extends AbstractController<SupplierGoodsService,SupplierGoods>{
	
	private static Timestamp now = new Timestamp(System.currentTimeMillis());
	
	/**
	 * @explain 分页条件查询用户   
	 * @param   param
	 * @return  JsonResult<IPage<T>>
	 * @author  戴艺辉
	 * @time    2019-06-11 09:49:42
	 */
    @RequestMapping(value = "/selectSupplierGoodsPages",method = RequestMethod.GET)
	@ApiOperation(value = "分页查询", notes = "分页查询返回[IPage<T>],作者：戴艺辉")
	public JsonResult<IPage<SupplierGoodsAndDataDto>> selectSupplierGoodsPages(PageParam<Map<String,Object>> param){
		JsonResult<IPage<SupplierGoodsAndDataDto>> returnPage=new JsonResult<IPage<SupplierGoodsAndDataDto>>();
		Page<Map<String,Object>> page=new Page<Map<String,Object>>(param.getPageNum(),param.getPageSize());
		QueryWrapper<Map<String,Object>> queryWrapper =new QueryWrapper<Map<String,Object>>();
		queryWrapper.setEntity(param.getParam());
		//分页数据
		IPage<SupplierGoodsAndDataDto> pageData=baseService.selectSupplierGoodsPages(page, queryWrapper.getEntity());
		returnPage.success(pageData);
		return returnPage;
	}
    
    
	/**
	 * 
	 * 供应价设置
	 *
	 * @param @param  context
	 * @param @return
	 * @param @throws CoreException
	 * @return
	 */
	@RequestMapping(value = "/saveSupplierPrice", method = RequestMethod.POST)
	@ApiOperation(value = "设置保存供应价", notes = "分页查询返回JsonResult<Object>,作者：戴艺辉")
	public JsonResult<Object> saveSupplierPrice(@RequestBody SupplierGoodsDto param) {
		JsonResult<Object> returnPage = new JsonResult<Object>();
		// 验证是否为空
		List<SupplierGoodsChangeDto> supplierGoodsList=param.getSupplierGoodsList();
		for (SupplierGoodsChangeDto supplierGoods : supplierGoodsList) {
			String supplyPrice=supplierGoods.getSupplyPrice();
			String taxRate=supplierGoods.getTaxRate();
			String payMethod=supplierGoods.getPayMethod();
			if(TextUtils.isEmptys(supplyPrice,taxRate,payMethod)) {
				log.info("参数为空");
				throw new BizException(ErrorCode.ILLEGAL_ARGUMENT.getCode(), "参数不能为空");
			}
 		}
		for (SupplierGoodsChangeDto supplierGoodsChange : supplierGoodsList) {
			
			SupplierGoods supplierGoods=new SupplierGoods();
			try {
				BeanUtils.copyProperties(supplierGoodsChange,supplierGoods);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			supplierGoods.setSupplyPrice(new BigDecimal(supplierGoodsChange.getSupplyPrice()).multiply(new BigDecimal("10000")).longValue());
			baseService.save(supplierGoods);
		}
		returnPage.success("设置供应价成功！");
		return returnPage;
	}
	
	
	
	


}