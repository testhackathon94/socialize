package com.amazon.domain.bean.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazon.domain.bean.GoodBuy;
import com.amazon.domain.bean.GoodBuyResponse;
import com.amazon.netty.dao.ProductDao;

public class GoodBuyVO {

	private List<GoodBuyResponseVO> goodBuyResponseVOList = new ArrayList<GoodBuyResponseVO>();
	
	private Date eventTime;
	
	
	public GoodBuyVO(GoodBuy goodBuy) {
		this.eventTime = goodBuy.getEventTime();
//		Set<GoodBuyResponse> gbrList = goodBuy.getGoodBuyResponseSet();
		ProductDao productDao = new ProductDao();
		List<GoodBuyResponse> gbrList =  productDao.fetchGoodBuyResponse(goodBuy.getId());
		for(GoodBuyResponse gbr : gbrList){
			GoodBuyResponseVO buyResponseVO = new GoodBuyResponseVO(gbr);
			addGoodBuyResponseVO(buyResponseVO);
		}
	}
	
	public void addGoodBuyResponseVO(GoodBuyResponseVO goodBuyResponseVO){
		
		goodBuyResponseVOList.add(goodBuyResponseVO);
		
	}


	public List<GoodBuyResponseVO> getGoodBuyResponseVOList() {
		return goodBuyResponseVOList;
	}


	public void setGoodBuyResponseVOList(
			List<GoodBuyResponseVO> goodBuyResponseVOList) {
		this.goodBuyResponseVOList = goodBuyResponseVOList;
	}


	public Date getEventTime() {
		return eventTime;
	}


	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	
	
	
}
