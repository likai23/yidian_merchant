/**
 * @filename:SupplierGoodsServiceImpl 2019-06-11 09:49:43
 * @project ydsh-saas-service-merchant  V1.0
 * Copyright(c) 2018 戴艺辉 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.merchant.web.service.impl;

import com.ydsh.merchant.web.entity.SupplierGoods;
import com.ydsh.merchant.web.entity.dto.SupplierGoodsAndDataDto;
import com.ydsh.merchant.web.dao.SupplierGoodsDao;
import com.ydsh.merchant.web.service.SupplierGoodsService;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**   
 * <p>自定义serviceImpl写在这里</p>
 * 
 * <p>说明： 供应商和商品的关系服务实现层</P>
 * @version: V1.0
 * @author: 戴艺辉
 * 
 */
@Service
public class SupplierGoodsServiceImpl  extends ServiceImpl<SupplierGoodsDao, SupplierGoods> implements SupplierGoodsService  {
	/**
	 * 
	* * 供应商和供应价连表查询
	*
	* @param @param page
	* @param @param queryWrapper
	* @param @return
	* @return
	 */
	  public Page<SupplierGoodsAndDataDto> selectSupplierGoodsPages(IPage<Map<String, Object>> page,@Param("queryWrapper") Map<String,Object> queryWrapper){
		  return baseMapper.selectSupplierGoodsPages(page, queryWrapper);
	  }
}