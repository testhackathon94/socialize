package com.amazon.netty.dao;

import java.net.URLEncoder;

import com.amazon.netty.database.DatabaseHTTPMgr;

public class ProductDao {
	
	public String getBillingProducts(String storeId){
		String sql="select quantity,in('product_has_inventory')[is_deleted = false] as rid,in('product_has_inventory')[is_deleted = false].name as name,in('product_has_inventory')[is_deleted = false].cost_price as cost_price,"
				+ "in('product_has_inventory')[is_deleted = false].full_price as full_price,in('product_has_inventory')[is_deleted = false].special_price as special_price,in('product_has_inventory')[is_deleted = false].price as price,"
				+ "in('product_has_inventory')[is_deleted = false].tax as tax,in('product_has_inventory')[is_deleted = false].tax.tax_value as tax_value,in('product_has_inventory')[is_deleted = false].tax.tax_name as tax_name,in('store_has_product_inventory')[is_deleted = false] as storeid   "
				+ "from (select expand(out('store_has_product_inventory')[is_deleted = false]) from "+storeId+")  where in('product_has_inventory')[0].is_deleted = false skip 0 limit -1";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}
}
