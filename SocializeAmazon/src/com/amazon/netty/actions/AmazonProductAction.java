package com.amazon.netty.actions;




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
	
	@Path(path="/review")
	public String reviewProduct(){
		System.out.println("Calling review product");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		Integer productId = this.bean.getProductId();
		String reviewContent = this.bean.getReview();
		return productSvc.activity(customerId, productId, reviewContent, "Reviewed");
	}
	
	@Path(path="/rate")
	public String rateProduct(){
		System.out.println("Calling review product");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		Integer productId = this.bean.getProductId();
		String rate = this.bean.getRate();
		return productSvc.activity(customerId, productId, rate, "Rated");
	}

	@Path(path="/goodBuy")
	public String isThisGoodBuy(){
		System.out.println("Calling good buy");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		Integer productId = this.bean.getProductId();
		String rate = this.bean.getRate();
		return productSvc.activity(customerId, productId, rate, "Good Buy");
	}

	@Path(path="/goodBuyResponse")
	public String goodBuyResponse(){
		System.out.println("Calling good buy");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		Integer goodBuyId = this.bean.getGoodBuyId();
		String comment = this.bean.getComment();
		String goodBuyResponse = this.bean.getGoodBuyResponse();
		return productSvc.writeGoodBuyResponse(goodBuyId, customerId, goodBuyResponse, comment);
	}

	
	@Path(path="/recommend")
	public String recommendProduct(){
		System.out.println("Calling review product");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		Integer productId = this.bean.getProductId();
		Boolean recommend = this.bean.getIsRecommended();
		return productSvc.activity(customerId, productId, recommend.toString(), "Recommended");
	}

	@Path(path="/sharePurchase")
	public String sharePurchase(){
		System.out.println("Calling review product");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		Integer productId = this.bean.getProductId();
		Boolean isPurchased = this.bean.getIsPurchased();
		return productSvc.activity(customerId, productId, isPurchased.toString(), "Purchased");
	}

	@Path(path="/addWish")
	public String addWish(){
		System.out.println("Calling review product");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		Integer productId = this.bean.getProductId();
		Boolean isWished = this.bean.getIsWished();
		return productSvc.activity(customerId, productId, isWished.toString(), "Wish List");
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
	
	@Path(path="/getMyFriendsActivity")
	public String fetchFriendsActivity(){
		try{
		System.out.println("Amazon Product Action...");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		return productSvc.fetchMyFriendsActivity(customerId);
		}catch(Throwable e){
			System.out.println("Exception while getting freinds feed");
			e.printStackTrace();
		}
		return "";
	}

	@Path(path="/productActivity")
	public String fetchProductActivity(){
		try{
		System.out.println("Amazon Product Action...");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer productId = this.bean.getProductId();
		Integer customerId = this.bean.getCustomerId();
		return productSvc.fetchProductActivity(productId, customerId);
		}catch(Throwable e){
			System.out.println("Exception while getting freinds feed");
			e.printStackTrace();
		}
		return "";
	}
	
	
	@Path(path="/getMyActivity")
	public String getMyActivity(){
		System.out.println("Amazon Product Action...");
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
		Integer customerId = this.bean.getCustomerId();
		return productSvc.getMyActivity(customerId);
	}


}

