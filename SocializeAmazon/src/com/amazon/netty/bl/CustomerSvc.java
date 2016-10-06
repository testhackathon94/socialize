package com.amazon.netty.bl;

import com.amazon.netty.bean.CustomerBean;
import com.amazon.netty.dao.CustomerDao;
import com.amazon.netty.dao.DaoManager;

public class CustomerSvc {
	
	public String getCustomerDetails(String storeId,String customerId){
		CustomerDao customerDao = DaoManager.getInstance().getCustomerDao();
		String records = customerDao.getCustomerDetails(storeId,customerId);
		return records;
	}
	
	public String getCustomerActivity(String storeId,String customerId,String skip,String limit){
		CustomerDao customerDao = DaoManager.getInstance().getCustomerDao();
		String records = customerDao.getCustomerActivity(storeId,customerId,skip,limit);
		return records;
	}
	
	public String getCustomerStatus(String storeId,String customerId,String fromDate,String toDate){
		CustomerDao customerDao = DaoManager.getInstance().getCustomerDao();
		String records = customerDao.getCustomerStatus(storeId,customerId,fromDate,toDate);
		return records;
	}
	
	public String getCustomerPreferedStaff(String storeId,String customerId){
		CustomerDao customerDao = DaoManager.getInstance().getCustomerDao();
		String records = customerDao.getCustomerPreferedStaff(storeId,customerId);
		return records;
	}
	
	public String getCustomerMemberShip(String networkId,String customerId){
		CustomerDao customerDao = DaoManager.getInstance().getCustomerDao();
		String records = customerDao.getCustomerMemberShip(networkId,customerId);
		return records;
	}
	
	public String getCustomerOffers(String storeId,String customerId){
		CustomerDao customerDao = DaoManager.getInstance().getCustomerDao();
		String records = customerDao.getCustomerOffers(storeId,customerId);
		return records;
	}
	
	public String getCustomerPoints(String networkId,String customerId){
		CustomerDao customerDao = DaoManager.getInstance().getCustomerDao();
		String records = customerDao.getCustomerPoints(networkId,customerId);
		return records;
	}
	
	public String getCustomerPointsActivity(String storeId,String customerId,String skip,String limit){
		CustomerDao customerDao = DaoManager.getInstance().getCustomerDao();
		String records = customerDao.getCustomerPointsActivity(storeId,customerId,skip,limit);
		return records;
	}

	public String storeSegmentCustomer(CustomerBean customerBean){
		CustomerDao customerDao = DaoManager.getInstance().getCustomerDao();    
		return customerBean.getSegment();
	}
}
