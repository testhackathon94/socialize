package com.amazon.netty.dao;

import java.net.URLEncoder;

import com.amazon.netty.bean.CommonBean;
import com.amazon.netty.database.DatabaseHTTPMgr;

public class CommonDao {
	
	public String getNotificationCount(String networkId){
		String sql = "select notification_count from "+networkId;
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}
	
	public String getNotification(String networkId){
		String sql = "select @rid,message,link,is_status,created_date from "
				+ "(select expand(out('network_has_notification')) from "+networkId+") where is_deleted = false order by created_date desc skip 0 limit 20";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}
	
	public String getNetworkInventoryDetails(String networkId){
		String sql = "select *,in('network_has_network_inventory')[0].out('cms_has_network')[is_status = false].is_free as free,in('network_has_network_inventory')[0].out('cms_has_network')[is_status = false].is_trial as trail,$day as remainingday,in('network_has_network_inventory')[0].out('network_has_membership_master')[is_deleted = false].aslist().size() as membership "
				+ "from (select expand(out('network_has_network_inventory')) from "+networkId+") let $day = eval('( package_end_date - sysdate() ) / (1000 * 60 * 60 * 24)')";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}
	
	public String getPaymentMode(String networkId){
		String sql = "select @rid as @rid,label,name from (traverse out_master_child from (select * from master_data where label='14_CUSTOMER')) where $depth = 1 skip 0 limit -1";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}
	
	public String getBillingSettings(String storeId){
		String sql = "select in('network_has_store')[0].edit_bill as edit_bill,enable_tax, apply_tax_type, invoice_text from "+storeId;
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getPointsMasterSetting(String networkId) {
		String sql = "select @rid,purchase_value,point_value,points,min_redeem_points,in('network_has_master_points')[0].is_reward_enable as status from (select expand(out('network_has_master_points')) from "+networkId+")";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getUserpermission(String userId) {
		// TODO Auto-generated method stub
		String sql = "select out('employee_has_permission')[is_status = true].feature.label.aslist() as permission from "+userId;
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getPackages(String type) {
		// TODO Auto-generated method stub
		String sql = "select @rid,name,price,photo_count,video_count,sms_count,email_count,customer_count,voucher_count,validity_month,staff_count,featurelabel,out('cmspackage_has_feature')[is_deleted = false].feature as features "
				+ "from (select * from cms_packages where is_deleted = false and is_trail = false and type='"+type+"' order by @rid asc) where is_deleted = false skip 0 limit -1";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getPackagePlan(String packId) {
		// TODO Auto-generated method stub
		String sql = "select @rid,label,month,discount,priority,created_date from (select expand(out('cms_packages_has_child_package')) "
				+ "from "+packId+" where is_deleted = false order by priority asc) skip 0 limit -1";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}
}
