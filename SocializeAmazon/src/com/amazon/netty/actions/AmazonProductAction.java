package com.amazon.netty.actions;




import com.amazon.domain.bean.Order;
import com.amazon.netty.bean.ProductBean;
import com.amazon.netty.bl.ProductSvc;
import com.amazon.netty.blm.AmazonServiceMgr;
import com.amazon.netty.controller.Path;
import com.amazon.netty.http.WHTTPResponse;
import com.amazon.netty.http.WHttpRequest;


@Path(path="/amz")
public class AmazonProductAction extends BaseAction{

	private ProductBean bean;
	
	public AmazonProductAction(WHttpRequest httpRequest, WHTTPResponse httpResponse) {
		super(httpRequest, httpResponse);
	}

	@Override
	public ProductBean getBean() {
		// TODO Auto-generated method stub
		return this.bean;
	}
	
	@Override
	public void setBean(Object beanObject) {
		this.bean = (ProductBean)beanObject;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class beanClass() {
		return ProductBean.class;
	} 
	
	
	public void getURI(){
		String uri = this.httpRequest.getURI();
		httpResponse.writeResponse(uri);
	}
	
	@Path(path="/lastOrder")
	public String fetchLastOrder(){
		System.out.println("Amazon Product Action...");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		return productSvc.fetchLastOrder(customerId);
	}

	@Path(path="/getUser")
	public String fetchCustomer(){
		System.out.println("Amazon Product Action...");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		return productSvc.fetchCustomerId(customerId);
	}

}
