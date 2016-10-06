package com.amazon.netty.dao;

import java.net.URLEncoder;

import com.amazon.netty.database.DatabaseHTTPMgr;

public class CustomerDao {

	public String getCustomerOffers(String storeId, String customerId) {
		// TODO Auto-generated method stub
		String voucher = "$a = (select @class as table_class, @rid as id, voucher_end_date, is_redeem, voucher_code, primary_term, secondary_term, created_date, validity, discount_value, purchase_value from (select expand(out('customer_has_voucher')[is_deleted = false][is_redeem = false]) from "+customerId+" where is_deleted = false) where in('store_has_generatedvoucher') in ("+storeId+") and sysdate() <= voucher_end_date order by voucher_end_date ), ";
		String customoffer = "$b = ( select @rid as rid, in_customer_has_custom_offer['out="+customerId+"']['@rid'] as id, @class as table_class, offer_text, offer_type, offer_title, created_date, is_track ,in_customer_has_custom_offer['out="+customerId+"'].is_used as is_used from (select expand(out('customer_has_custom_offer')) from "+customerId+" where is_deleted = 0) where is_track = 1 and in_customer_has_custom_offer['out="+customerId+"'].is_used=0 and in('store_has_custom_offer') in ("+storeId+") and (end_date >= sysdate() or end_date is null) order by created_date ), ";
		String greetings = "$c = ( select @rid as id,@class as table_class,out_customer_has_greetingsoffer.aslist()[0] as edgeofcustomeroffer,couponcode, in('schedule_has_offercode')[0].out('schedule_has_offercode')[0].in('schedule_has_offercode')[0].out('schedule_has_bundle')[0].out('bundle_has_sms')[0].smstext as sms_text from (traverse out_schedule_has_offercode from (select * from (traverse in_store_has_schedule_greetings from "+storeId+") where @class='schedule_greetings' )) where @class='greetings_offercode' and out_customer_has_greetingsoffer.in in "+customerId+" and out_customer_has_greetingsoffer.aslist()[0].is_redeemstatus = 0 ), ";
		String sql = "select expand( $d ) let "+voucher+customoffer+greetings+"$d = UNIONALL( $a, $b, $c ) skip 0 limit -1";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getCustomerPoints(String networkId, String customerId) {
		// TODO Auto-generated method stub
		String sql="select @rid,points from (select expand(out('customer_has_points_inventory')['network="+networkId+"']) from "+customerId+")";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}
	
	public String getCustomerPointsActivity(String storeId,String customerId,String skip,String limit){
		String sql="select points,is_credit,created_date,bill_no from (select expand(out('customer_has_points_transaction')) "
				+ "from "+customerId+") where in('store_has_points_transaction') in ("+storeId+") order by @rid desc skip "+skip+" limit "+limit;
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getCustomerDetails(String storeId, String customerId) {
		// TODO Auto-generated method stub
		String sql="select $a[0].total as total_amount, @rid as customer_id, name, gender, mobile_no, email_id, location, dob.format('dd-MM-yyyy'), anniversary_date.format('dd-MM-yyyy'),out('customer_has_customer_feedback')['is_deleted = false']['in_store_has_customer_feedback = "+storeId+"'].bill_amount.aslist() as total_amount1, "
				+ "out('customer_has_activity').aslist()['is_deleted = false']['store_id = "+storeId+"'].size() as total_count from "+customerId+" let $a = (select sum(bill_amount.asFloat()) as total from (select expand(out('customer_has_customer_feedback')['is_deleted = false']) from "+customerId+") where in('store_has_customer_feedback') in "+storeId+" and bill_amount <>= '')";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getCustomerActivity(String storeId, String customerId,String skip,String limit) {
		// TODO Auto-generated method stub
		String sql="select @rid as id, feedback, service, bill_amount, bill_no, customer_service.service_name.aslist(), created_date, is_sale, service_person.person_name from (select expand(out('customer_has_customer_feedback')['is_deleted = false']) "
				+ "from "+customerId+") where in('store_has_customer_feedback') in ("+storeId+") and customer_service.aslist().size() > 0 order by created_date desc skip "+skip+" limit "+limit;
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getCustomerStatus(String storeId, String customerId, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		String sql="select count(*) as count_value from (select expand(out('customer_has_activity')[store_id = "+storeId+"]) from "+customerId+") where created_date between '"+fromDate+"' and '"+toDate+"'";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getCustomerPreferedStaff(String storeId, String customerId) {
		// TODO Auto-generated method stub
		String sql="select count(1) as count, employee_id.person_name as employee_name, employee_id, service_id, preferred_status "
				+ "from (select expand(out('customer_has_preferred_stylest')) from "+customerId+") where is_deleted = false and service_id.in('store_has_store_service') in "+storeId+" and employee_id.in('store_has_service_person') "
						+ "in "+storeId+" and preferred_status = 1 order by count desc group by employee_id";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getCustomerMemberShip(String networkId, String customerId) {
		// TODO Auto-generated method stub
		String sql="select @rid as masterid from (select expand(out('customer_has_membership')) from "+customerId+") where "
				+ "in('network_has_generated_membership') in ("+networkId+") and sysdate() < expiry_date or expiry_date is null";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

}
