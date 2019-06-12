package com.ydsh.merchant.dto;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.ydsh.merchant.web.entity.CustomerData;
/**
 * 项目名称：
 * 类名称：
 * 类描述：客户管理转换
 * 创建人：daiyihui-戴艺辉
 * 邮箱：daiyihui@yidianlife.com
 * 创建时间：2019/4/28 15:21
 * 修改备注：
 * @version1.0
 *
 */
public class CustomerDataAdapters {
	public static Map<String, Object> convertSystemBaseConfigToMap(Map<String,Object> systemBaseConfig) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", systemBaseConfig.get("id"));
		map.put("cdNo",systemBaseConfig.get("cd_no"));
		map.put("customerName", systemBaseConfig.get("customer_name"));
		map.put("saleMain", systemBaseConfig.get("sale_main"));
		map.put("customerType", systemBaseConfig.get("customer_type"));
		map.put("customerPlatformIds",systemBaseConfig.get("customer_platform_ids"));
		map.put("salesManId", systemBaseConfig.get("sales_man_id"));
		map.put("customerPhone", systemBaseConfig.get("customer_phone"));
		map.put("reviewStatus",systemBaseConfig.get("review_status"));
		map.put("submitTime", systemBaseConfig.get("submit_time"));
		map.put("reviewTime", systemBaseConfig.get("review_time"));
		map.put("reviewId", systemBaseConfig.get("review_id"));
		map.put("customerStatus", systemBaseConfig.get("customer_status"));
		return map;
	}

	/**
	 * List转Map
	 * @param systemBaseConfigs
	 * @return
	 */
	public static List<Map<String, Object>> convertSystemBaseConfigToList(List<Map<String,Object>> systemBaseConfigs) {
		List<Map<String,Object>> list = new ArrayList<>();
		for (Map<String,Object> systemBaseConfig : systemBaseConfigs) {
			list.add(convertSystemBaseConfigToMap(systemBaseConfig));
		}
		return list;
	}
}
