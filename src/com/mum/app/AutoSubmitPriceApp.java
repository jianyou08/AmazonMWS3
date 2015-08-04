/*******************************************************************************
 * Copyright 2009-2015 Amazon Services. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 *
 * You may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations under the License.
 *******************************************************************************
 * Marketplace Web Service Products
 * API Version: 2011-10-01
 * Library Version: 2015-02-13
 * Generated: Tue Feb 10 14:34:49 PST 2015
 */
package com.mum.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;

import java.io.FileWriter;
import java.io.IOException;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.FeedSubmissionInfo;
import com.amazonaws.mws.model.ResponseMetadata;
import com.amazonaws.mws.model.SubmitFeedRequest;
import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonaws.mws.model.SubmitFeedResult;
import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;


/** Sample call for GetMatchingProduct. */
public class  AutoSubmitPriceApp {
	class MyPriceInfo {
		public String asin;
		
		//myprice info
		public boolean  has_set_myprice = false;
		public Float landedPrice;
		public Float listingPrice;	//当前价格，包含促销商品
		public Float shippingPrice;
		public Float regularPrice;	//当前价格，不包含促销商品
		public String fulfillmentChannel;
		public String itemCondition;
		public String itemSubCondition;
		public String sellerId;
		public String sellerSKU;
		
		public boolean has_set_competitive = false;
		public Float competitive_LandedPrice;
		public Float competitive_ListingPrice;
		public Float competitive_Shipping;
		
		//LowestOfferListing
		public boolean has_set_lowest = false;
		public int    lowest_NumberOfOfferListingsConsidered;
		public int    lowest_SellerFeedbackCount;
		public Float  lowest_LandedPrice;
		public Float  lowest_ListingPrice;
		public Float  lowest_Shipping;
		public String lowest_MultipleOffersAtLowestPrice;
		
		public static final String FULFILLMENT_CHANNEL_AMAZON = "AMAZON";
		public static final String FULFILLMENT_CHANNEL_MERCHANT = "MERCHANT";
		
		public String toString() {
            String res = " MyPriceInfo{";
            res += "\n asin              :" + asin;
            res += "\n landedPrice       :" + landedPrice;
            res += "\n listingPrice      :" + listingPrice;
            res += "\n shippingPrice     :" + shippingPrice;
            res += "\n regularPrice      :" + regularPrice;
            res += "\n fulfillmentChannel:" + fulfillmentChannel;
            res += "\n itemCondition     :" + itemCondition;
            res += "\n itemSubCondition  :" + itemSubCondition;
            res += "\n sellerId          :" + sellerId;
            res += "\n sellerSKU         :" + sellerSKU;
            if ( has_set_competitive )
            {
                res += "\n competitive_LandedPrice              :" + competitive_LandedPrice;
                res += "\n competitive_ListingPrice             :" + competitive_ListingPrice;
                res += "\n competitive_Shipping                 :" + competitive_Shipping;
            }
            if ( has_set_lowest )
            {
                res += "\n lowest_NumberOfOfferListingsConsidered:" + lowest_NumberOfOfferListingsConsidered;
                res += "\n lowest_SellerFeedbackCount            :" + lowest_SellerFeedbackCount;
                res += "\n lowest_LandedPrice                    :" + lowest_LandedPrice;
                res += "\n lowest_ListingPrice                   :" + lowest_ListingPrice;
                res += "\n lowest_Shipping                       :" + lowest_Shipping;
                res += "\n lowest_MultipleOffersAtLowestPrice    :" + lowest_MultipleOffersAtLowestPrice;
            }
            res += "\n}";
            return res;
		}
		
		
		public String toFormatString(String dtstring) {
            String res = dtstring;
            res += "," + asin;
            res += "," + landedPrice;
            res += "," + listingPrice;
            res += "," + shippingPrice;
            res += "," + regularPrice;
            res += "," + fulfillmentChannel;
            res += "," + itemCondition;
            res += "," + itemSubCondition;
            res += "," + sellerId;
            res += "," + sellerSKU;
            if ( has_set_competitive )
            {
                res += "," + competitive_LandedPrice;
                res += "," + competitive_ListingPrice;
                res += "," + competitive_Shipping;
            }
            else {
            	res += ",,,";
            }
            if ( has_set_lowest )
            {
                res += "," + lowest_NumberOfOfferListingsConsidered;
                res += "," + lowest_SellerFeedbackCount;
                res += "," + lowest_LandedPrice;
                res += "," + lowest_ListingPrice;
                res += "," + lowest_Shipping;
                res += "," + lowest_MultipleOffersAtLowestPrice;
            }
            else {
            	res += ",,,,,,";
            }
            res += ",,,,,";
            return res;
		}
		
	}
	private Map<String, MyPriceInfo> mapMyPriceInfo = new HashMap<String, MyPriceInfo>();
  
    
    
    public GetMyPriceForASINResponse GetMyPriceForASIN(
            MarketplaceWebServiceProducts client, 
            GetMyPriceForASINRequest request) {
        try {
            // Call the service.
            GetMyPriceForASINResponse response = client.getMyPriceForASIN(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: "+rhmd.getRequestId());
            System.out.println("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            List<GetMyPriceForASINResult> resList = response.getGetMyPriceForASINResult();
            for ( GetMyPriceForASINResult resItem : resList ) {
            	OffersList offerList = resItem.getProduct().getOffers();
            	for ( OfferType offer : offerList.getOffer() ) {
            		String asin = resItem.getASIN();;
            		
            		MyPriceInfo priceInfo = new MyPriceInfo();
            		priceInfo.asin              = asin;
            		priceInfo.landedPrice 		= offer.getBuyingPrice().getLandedPrice().getAmount().floatValue();
            		priceInfo.listingPrice 		= offer.getBuyingPrice().getListingPrice().getAmount().floatValue();
            		priceInfo.shippingPrice 	= offer.getBuyingPrice().getShipping().getAmount().floatValue();
            		priceInfo.regularPrice 		= offer.getRegularPrice().getAmount().floatValue();
            		priceInfo.fulfillmentChannel= offer.getFulfillmentChannel();
            		priceInfo.itemCondition		= offer.getItemCondition();
            		priceInfo.itemSubCondition	= offer.getItemSubCondition();
            		priceInfo.sellerId			= offer.getSellerId();
            		priceInfo.sellerSKU			= offer.getSellerSKU();
            		
            		MyLog.log.info(asin + priceInfo.toString());
            		
            		mapMyPriceInfo.put(asin, priceInfo);
            	}
            }
            return response;
        } catch (MarketplaceWebServiceProductsException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    }
    
    public GetCompetitivePricingForASINResponse GetCompetitivePrice(
            MarketplaceWebServiceProducts client, 
            GetCompetitivePricingForASINRequest request) {
        try {
            // Call the service.
            GetCompetitivePricingForASINResponse response = client.getCompetitivePricingForASIN(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: "+rhmd.getRequestId());
            System.out.println("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            MyLog.log.info(responseXml);
            
            List<GetCompetitivePricingForASINResult> resList = response.getGetCompetitivePricingForASINResult();
            for (GetCompetitivePricingForASINResult resItem : resList)
            {
            	List<CompetitivePriceType> priceList = resItem.getProduct().getCompetitivePricing().getCompetitivePrices().getCompetitivePrice();
            	for (CompetitivePriceType priceItem : priceList)
            	{
            		String asin = resItem.getASIN();
            		//Float  price = priceItem.getPrice().getLandedPrice().getAmount().floatValue();
            		Float  price = priceItem.getPrice().getListingPrice().getAmount().floatValue();
            		//System.out.println("Cpt:" + asin + ':' + price);
            		//MyLog.log.info("Cpt:" + asin + ':' + price);
            		
            		MyPriceInfo priceObj = mapMyPriceInfo.get(asin);
            		if ( null != priceObj ) {
            			priceObj.has_set_competitive      = true;
    					priceObj.competitive_LandedPrice  = priceItem.getPrice().getLandedPrice().getAmount().floatValue();
    					priceObj.competitive_ListingPrice = priceItem.getPrice().getListingPrice().getAmount().floatValue();
    					priceObj.competitive_Shipping 	  = priceItem.getPrice().getShipping().getAmount().floatValue();				
    					MyLog.log.info(asin + priceObj.toString());
            		}
            	}
            }
            
            return response;
        } catch (MarketplaceWebServiceProductsException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    }
    
    
    public GetLowestOfferListingsForASINResponse GetLowestOfferListingsForASIN(
            MarketplaceWebServiceProducts client, 
            GetLowestOfferListingsForASINRequest request) {
        try {
            // Call the service.
            GetLowestOfferListingsForASINResponse response = client.getLowestOfferListingsForASIN(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: "+rhmd.getRequestId());
            System.out.println("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            MyLog.log.info(responseXml);
            List<GetLowestOfferListingsForASINResult> resList = response.getGetLowestOfferListingsForASINResult();
            for ( GetLowestOfferListingsForASINResult resItem : resList ) {
            	String asin = resItem.getASIN();
            	Product product = resItem.getProduct();
            	List<LowestOfferListingType> offerList = product.getLowestOfferListings().getLowestOfferListing();
            	for ( LowestOfferListingType offer : offerList ) {
            		String fulfillmentChannel = offer.getQualifiers().getFulfillmentChannel();
            		String itemCondition      = offer.getQualifiers().getItemCondition();
            		String itemSubCondition   = offer.getQualifiers().getItemSubcondition();
            		
            		Float price = offer.getPrice().getLandedPrice().getAmount().floatValue();
            		MyPriceInfo priceObj = mapMyPriceInfo.get(asin);
            		if ( null != priceObj ) {
            			
            			if ( priceObj.itemCondition.toUpperCase().equals(itemCondition.toUpperCase())
            					&& priceObj.itemSubCondition.toUpperCase().equals(itemSubCondition.toUpperCase())
            					&& priceObj.fulfillmentChannel.toUpperCase().equals(fulfillmentChannel.toUpperCase()) )
            			{
            				if ( (null == priceObj.lowest_LandedPrice) || (priceObj.lowest_LandedPrice > price) ) 
            				{
            					priceObj.has_set_lowest      = true;
            					priceObj.lowest_LandedPrice  = offer.getPrice().getLandedPrice().getAmount().floatValue();
            					priceObj.lowest_ListingPrice = offer.getPrice().getListingPrice().getAmount().floatValue();
            					priceObj.lowest_Shipping 	 = offer.getPrice().getShipping().getAmount().floatValue();
            					priceObj.lowest_NumberOfOfferListingsConsidered = offer.getNumberOfOfferListingsConsidered();
            					priceObj.lowest_SellerFeedbackCount             = offer.getSellerFeedbackCount();
            					priceObj.lowest_MultipleOffersAtLowestPrice	    = offer.getMultipleOffersAtLowestPrice();
            					MyLog.log.info(asin + priceObj.toString());
            				}
            			}
            		}
            	}
            }
            return response;
        } catch (MarketplaceWebServiceProductsException ex) {
            // Exception properties are important for diagnostics.
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                System.out.println("RequestId: "+rhmd.getRequestId());
                System.out.println("Timestamp: "+rhmd.getTimestamp());
            }
            System.out.println("Message: "+ex.getMessage());
            System.out.println("StatusCode: "+ex.getStatusCode());
            System.out.println("ErrorCode: "+ex.getErrorCode());
            System.out.println("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    }
    
    public void SubmitPriceFeed(MarketplaceWebService service, SubmitFeedRequest request) {
        try {
            SubmitFeedResponse response = service.submitFeed(request);

            System.out.println("SubmitFeed Action Response");
            System.out
            .println("=============================================================================");
            System.out.println();

            System.out.print("    SubmitFeedResponse");
            System.out.println();
            if (response.isSetSubmitFeedResult()) {
                System.out.print("        SubmitFeedResult");
                System.out.println();
                SubmitFeedResult submitFeedResult = response
                .getSubmitFeedResult();
                if (submitFeedResult.isSetFeedSubmissionInfo()) {
                    System.out.print("            FeedSubmissionInfo");
                    System.out.println();
                    FeedSubmissionInfo feedSubmissionInfo = submitFeedResult
                    .getFeedSubmissionInfo();
                    if (feedSubmissionInfo.isSetFeedSubmissionId()) {
                        System.out.print("                FeedSubmissionId");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getFeedSubmissionId());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetFeedType()) {
                        System.out.print("                FeedType");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getFeedType());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetSubmittedDate()) {
                        System.out.print("                SubmittedDate");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getSubmittedDate());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetFeedProcessingStatus()) {
                        System.out
                        .print("                FeedProcessingStatus");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo.getFeedProcessingStatus());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetStartedProcessingDate()) {
                        System.out
                        .print("                StartedProcessingDate");
                        System.out.println();
                        System.out
                        .print("                    "
                                + feedSubmissionInfo
                                .getStartedProcessingDate());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetCompletedProcessingDate()) {
                        System.out
                        .print("                CompletedProcessingDate");
                        System.out.println();
                        System.out.print("                    "
                                + feedSubmissionInfo
                                .getCompletedProcessingDate());
                        System.out.println();
                    }
                }
            }
            if (response.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata responseMetadata = response
                .getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                "
                            + responseMetadata.getRequestId());
                    System.out.println();
                }
            }
            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();
            System.out.println();

        } catch (MarketplaceWebServiceException ex) {

            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
    }
    
    
    public void CheckAndSubmitPrice() {
    	Date dt = new Date();
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String dtstr = format.format(dt);
		try {
			FileWriter fileWriter = new FileWriter(AutoSubmitPriceConfig.historyPriceFile, true);
			for(Entry<String, MyPriceInfo> entry : mapMyPriceInfo.entrySet()){
	    		if ( entry.getValue().landedPrice != entry.getValue().lowest_LandedPrice ) {
	    			MyLog.log.info("Adjust Price:" + entry.getKey() + "==============" +  dtstr);
					if ( entry.getValue().landedPrice < entry.getValue().competitive_LandedPrice  ) {
						MyLog.log.info(entry.getKey() + " LE CompetitivePrice");
					}
					else if ( entry.getValue().landedPrice.equals(entry.getValue().competitive_LandedPrice) ) {
						MyLog.log.info(entry.getKey() + " EQ CompetitivePrice");
					}
					else {
						MyLog.log.info(entry.getKey() + " HQ CompetitivePrice");
					}
					if ( entry.getValue().landedPrice < entry.getValue().lowest_LandedPrice  ) {
						MyLog.log.info(entry.getKey() + " LE LowestPrice");
					}
					else if ( entry.getValue().landedPrice.equals(entry.getValue().lowest_LandedPrice) ) {
						MyLog.log.info(entry.getKey() + " EQ LowestPrice");
					}
					else {
						MyLog.log.info(entry.getKey() + " HQ LowestPrice");
					}
					fileWriter.write(entry.getValue().toFormatString(dtstr) + "\n"); 
	    		}
			}
	    	fileWriter.flush();
	     	fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	/*SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(AutoSubmitPriceConfig.sellerId);
        request.setMWSAuthToken(AutoSubmitPriceConfig.sellerDevAuthToken);
        request.setMarketplaceIdList(new IdList(Arrays.asList(
        		AutoSubmitPriceConfig.marketplaceId)));

        request.setFeedType("_POST_PRODUCT_PRICING_DATA_");
        try {
			request.setFeedContent( new FileInputStream("my-feed.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
    	SubmitPriceFeed(AutoSubmitPriceConfig.getServiceClient(), request);*/
    }

    
    public void AppendToHistoryFile(String filenaem) {
    	FileWriter fileWriter;
		try {
			long timestamp = System.currentTimeMillis();
			Date dt = new Date();
			fileWriter = new FileWriter(filenaem, true);
			for(Entry<String, MyPriceInfo> entry : mapMyPriceInfo.entrySet()){ 
				String line;
				//line = timestamp + "," + entry.getKey() + ',' + entry.getValue().competitivePrice + "," + entry.getValue().myPrice + "," + dt.toString();
				//fileWriter.write(line + "\r\n"); 

				//if ( entry.getValue().competitivePrice != entry.getValue().myPrice ) {
				//	MyLog.log.info("Adjust Price:" + line);
				//}
				//else 
				{
					//MyLog.log.info("Normal:" + line);
				}
			}
    	   fileWriter.flush();
    	   fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     *  Command line entry point.
     */
    public static void main(String[] args) {
    	String confFileName = "E:/workspace/java/AmazonMWS3/dist/auto_update_price.xml";
    	if ( args.length > 0) {
    		confFileName = args[0];
    	}
    	int ret = AutoSubmitPriceConfig.InitConfig(confFileName);
    	if ( ret != 0 )
    	{
    		System.out.println("InitConf error:" + confFileName);
    		MyLog.log.log(Level.WARNING, "InitConf error:" + confFileName);
    		return;
    	}

    	System.out.println(AutoSubmitPriceConfig.toTxt());
    	
    	MarketplaceWebServiceProductsClient client = AutoSubmitPriceConfig.getProductClient();
    	AutoSubmitPriceApp handler = new AutoSubmitPriceApp();
    	
    	
    	//Get MyPrice
    	if ( true ){
	        GetMyPriceForASINRequest request = new GetMyPriceForASINRequest();
	        request.setSellerId(AutoSubmitPriceConfig.sellerId);
	        request.setMWSAuthToken(AutoSubmitPriceConfig.sellerDevAuthToken);
	        request.setMarketplaceId(AutoSubmitPriceConfig.marketplaceId);
	        ASINListType asinList = new ASINListType();
	        asinList.setASIN(AutoSubmitPriceConfig.reqAsinList);
	        request.setASINList(asinList);
	
	        // Make the call.
	        handler.GetMyPriceForASIN(client, request);
    	}
    	
    	//Get Competitive Price
    	if ( true )
    	{
	        GetCompetitivePricingForASINRequest request = new GetCompetitivePricingForASINRequest();
	        request.setSellerId(AutoSubmitPriceConfig.sellerId);
	        request.setMWSAuthToken(AutoSubmitPriceConfig.sellerDevAuthToken);
	        request.setMarketplaceId(AutoSubmitPriceConfig.marketplaceId);
	        ASINListType asinList = new ASINListType();
	        asinList.setASIN(AutoSubmitPriceConfig.reqAsinList);
	        request.setASINList(asinList);
	
	        // Make the call.
	        handler.GetCompetitivePrice(client, request);
    	}
    	
    	//get Lowest Price
    	if ( true ) {
			GetLowestOfferListingsForASINRequest request = new GetLowestOfferListingsForASINRequest();
			request.setSellerId(AutoSubmitPriceConfig.sellerId);
	        request.setMWSAuthToken(AutoSubmitPriceConfig.sellerDevAuthToken);
	        request.setMarketplaceId(AutoSubmitPriceConfig.marketplaceId);
	        ASINListType asinList = new ASINListType();
	        asinList.setASIN(AutoSubmitPriceConfig.reqAsinList);
	        request.setASINList(asinList);
			String itemCondition = "new";
			request.setItemCondition(itemCondition);
			Boolean excludeMe = Boolean.valueOf(false);
			request.setExcludeMe(excludeMe);
			
			// Make the call.
			handler.GetLowestOfferListingsForASIN(client, request);
    	}
    	
    	//chenck and submit 
    	if ( true )
    	{
    		handler.CheckAndSubmitPrice();
    	}
    	
    	//handler.AppendToHistoryFile(AutoSubmitPriceConfig.historyPriceFile);
    }
}
