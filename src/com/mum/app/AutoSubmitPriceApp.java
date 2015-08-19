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

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.FeedSubmissionInfo;
import com.amazonaws.mws.model.IdList;
import com.amazonaws.mws.model.ResponseMetadata;
import com.amazonaws.mws.model.SubmitFeedRequest;
import com.amazonaws.mws.model.SubmitFeedResponse;
import com.amazonaws.mws.model.SubmitFeedResult;
import com.amazonservices.mws.client.MwsXmlBuilder;
import com.amazonservices.mws.client.MwsXmlReader;
import com.amazonservices.mws.client.MwsXmlWriter;
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
  
	private String computeContentMD5HeaderValue( InputStream fis ) 
        throws IOException, NoSuchAlgorithmException {
        
        DigestInputStream dis = new DigestInputStream( fis, MessageDigest.getInstance( "MD5" ));
            
        byte[] buffer = new byte[8192];
        while( dis.read( buffer ) > 0 );
            
        String md5Content = new String( org.apache.commons.codec.binary.Base64.encodeBase64(dis.getMessageDigest().digest()) );           
        return md5Content;
    }
    
    public GetMyPriceForASINResponse GetMyPriceForASIN(
            MarketplaceWebServiceProducts client, 
            GetMyPriceForASINRequest request) {
        try {
            // Call the service.
            GetMyPriceForASINResponse response = client.getMyPriceForASIN(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            MyLog.log.info("Response:");
            MyLog.log.info("RequestId: "+rhmd.getRequestId());
            MyLog.log.info("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            MyLog.log.info(responseXml);
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
            MyLog.log.info("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                MyLog.log.info("RequestId: "+rhmd.getRequestId());
                MyLog.log.info("Timestamp: "+rhmd.getTimestamp());
            }
            MyLog.log.info("Message: "+ex.getMessage());
            MyLog.log.info("StatusCode: "+ex.getStatusCode());
            MyLog.log.info("ErrorCode: "+ex.getErrorCode());
            MyLog.log.info("ErrorType: "+ex.getErrorType());
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
            MyLog.log.info("Response:");
            MyLog.log.info("RequestId: "+rhmd.getRequestId());
            MyLog.log.info("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            MyLog.log.info(responseXml);
            MyLog.log.info(responseXml);
            
            List<GetCompetitivePricingForASINResult> resList = response.getGetCompetitivePricingForASINResult();
            for (GetCompetitivePricingForASINResult resItem : resList)
            {
            	List<CompetitivePriceType> priceList = resItem.getProduct().getCompetitivePricing().getCompetitivePrices().getCompetitivePrice();
            	for (CompetitivePriceType priceItem : priceList)
            	{
            		String asin = resItem.getASIN();
            		
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
            MyLog.log.info("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                MyLog.log.info("RequestId: "+rhmd.getRequestId());
                MyLog.log.info("Timestamp: "+rhmd.getTimestamp());
            }
            MyLog.log.info("Message: "+ex.getMessage());
            MyLog.log.info("StatusCode: "+ex.getStatusCode());
            MyLog.log.info("ErrorCode: "+ex.getErrorCode());
            MyLog.log.info("ErrorType: "+ex.getErrorType());
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
            MyLog.log.info("Response:");
            MyLog.log.info("RequestId: "+rhmd.getRequestId());
            MyLog.log.info("Timestamp: "+rhmd.getTimestamp());
            String responseXml = response.toXML();
            MyLog.log.info(responseXml);
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
            MyLog.log.info("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if(rhmd != null) {
                MyLog.log.info("RequestId: "+rhmd.getRequestId());
                MyLog.log.info("Timestamp: "+rhmd.getTimestamp());
            }
            MyLog.log.info("Message: "+ex.getMessage());
            MyLog.log.info("StatusCode: "+ex.getStatusCode());
            MyLog.log.info("ErrorCode: "+ex.getErrorCode());
            MyLog.log.info("ErrorType: "+ex.getErrorType());
            throw ex;
        }
    }
    
    public void SubmitPriceFeed(MarketplaceWebService service, SubmitFeedRequest request) {
        try {
            SubmitFeedResponse response = service.submitFeed(request);

            MyLog.log.info("SubmitFeed Ok:");
            MyLog.log.info(response.toXML());

        } catch (MarketplaceWebServiceException ex) {

            MyLog.log.info("Caught Exception: " + ex.getMessage());
            MyLog.log.info("Response Status Code: " + ex.getStatusCode());
            MyLog.log.info("Error Code: " + ex.getErrorCode());
            MyLog.log.info("Error Type: " + ex.getErrorType());
            MyLog.log.info("Request ID: " + ex.getRequestId());
            MyLog.log.info("XML: " + ex.getXML());
            MyLog.log.info("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
    }
    
    
    public void logPrice2File() {
    	Date dt = new Date();
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String dtstr = format.format(dt);
		try {
			FileWriter fileWriter = new FileWriter(AutoSubmitPriceConfig.historyPriceFile, true);
			for(Entry<String, MyPriceInfo> entry : mapMyPriceInfo.entrySet()){
	    		fileWriter.write(entry.getValue().toFormatString(dtstr) + "\n");
			}
	    	fileWriter.flush();
	     	fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void queryProductPrice() {
    	MarketplaceWebServiceProductsClient client = AutoSubmitPriceConfig.getProductClient();
    	
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
	        GetMyPriceForASIN(client, request);
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
	        GetCompetitivePrice(client, request);
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
			GetLowestOfferListingsForASIN(client, request);
    	}
    }
    
    public Float CheckStrategy(String asin, MyPriceInfo nowPrice) {
		MyLog.log.info("###### Check strategy for :" + asin);
		
		ProductStrategy strategy = AutoSubmitPriceConfig.strategyMap.get(asin);
		if ( (null == strategy) || !strategy.isValid() ) {
			MyLog.log.info("No strategy for :" + asin);
			return null;
		}
		
		
		boolean isCmpCompetitive = strategy.cmpType.toLowerCase().equals("competitive");
		boolean isCmpLanded = strategy.cmpPrice.toLowerCase().equals("landed");
		MyLog.log.info("strategy cmpType:\"" + strategy.cmpType + "\"(" + isCmpCompetitive + ")  cmpPrice:\"" +  strategy.cmpPrice +"\"(" + isCmpLanded + ")");
		
		Float cmp_myPrice = null;
		if ( isCmpLanded ) {
			cmp_myPrice = nowPrice.landedPrice;
		} else {
			cmp_myPrice = nowPrice.listingPrice;
		}
		
		Float cmp_otherPrice = null;
		Float newMyPrice = null;
		if ( isCmpCompetitive ) {
			newMyPrice = nowPrice.competitive_ListingPrice;
			if ( isCmpLanded ) {
				cmp_otherPrice = nowPrice.competitive_LandedPrice;
			} else {
				cmp_otherPrice = nowPrice.competitive_ListingPrice;
			}
		} else {
			newMyPrice = nowPrice.lowest_ListingPrice;
			if ( isCmpLanded ) {
				cmp_otherPrice = nowPrice.lowest_LandedPrice;
			} else {
				cmp_otherPrice = nowPrice.lowest_ListingPrice;
			}
		}
		
		MyLog.log.log(Level.INFO, "cmp_myPrice(" + cmp_myPrice + ") - cmp_otherPrice(" + cmp_otherPrice + ")=" + (cmp_myPrice - cmp_otherPrice) + " conditionRange[>" + strategy.condition_mypriceHigh + ", or <-" + strategy.condition_mypriceLow);
		if ( (cmp_myPrice - cmp_otherPrice >= strategy.condition_mypriceHigh) || (cmp_otherPrice - cmp_myPrice >= strategy.condition_mypriceLow) ) {
			newMyPrice += strategy.setDiffPrice;
			MyLog.log.log(Level.INFO, "New price:" + asin + "=" + newMyPrice + "(" + cmp_myPrice + ") range[" + strategy.priceRangeMin + "," + strategy.priceRangeMax + "]");
			if ( (strategy.priceRangeMin <= newMyPrice) && (newMyPrice <= strategy.priceRangeMax) ) {
				float diffPrice = newMyPrice - nowPrice.listingPrice;
				if ( (-0.0001 <= diffPrice) && (diffPrice <= -0.001) ) {
					MyLog.log.log(Level.INFO, "newMyPrice(" + newMyPrice + ") equry nowPrice(" + nowPrice.listingPrice + ") no need submit");
					return null;
				}
				MyLog.log.log(Level.INFO, "In range price, will be submit" + newMyPrice);
				return newMyPrice;
			}
			else {
				MyLog.log.log(Level.INFO, "Over range price");
			}
		}
		
		return null;
	}
    
    public String BuildSubmitPriceXml(Map<String, Float> submitSkuMap) {
    	XmlBuilder xmlBuilder = new XmlBuilder();
    	xmlBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
    	xmlBuilder.beginObject("AmazonEnvelope");
    	xmlBuilder.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
    	xmlBuilder.writeAttribute("xsi:noNamespaceSchemaLocation", "amznenvelope.xsd");
    	
    	xmlBuilder.beginObject("Header");
    	xmlBuilder.write("DocumentVersion", "1.01");
    	xmlBuilder.write("MerchantIdentifier", AutoSubmitPriceConfig.sellerId);
    	xmlBuilder.endObject("Header");
    	
    	xmlBuilder.write("MessageType", "Price");
    	
    	int msgid = 1;
    	for ( Entry<String, Float> entry : submitSkuMap.entrySet() ) {
	    	xmlBuilder.beginObject("Message");
	    	xmlBuilder.write("MessageID", msgid);
	    	xmlBuilder.beginObject("Price");
	    	xmlBuilder.write("SKU", entry.getKey());
	    	xmlBuilder.beginObject("StandardPrice");
	    	xmlBuilder.writeAttribute("currency", "USD");
	    	//保留两位小数
	    	BigDecimal b = new BigDecimal(entry.getValue()); 
	    	Float fvalue = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue(); 
	    	xmlBuilder.writeValue(fvalue.toString());
	    	
	    	xmlBuilder.endObject("StandardPrice");
	    	xmlBuilder.endObject("Price");
	    	xmlBuilder.endObject("Message");
	    	msgid++;
    	}
    	
    	xmlBuilder.endObject("AmazonEnvelope");
    	return xmlBuilder.toString();
    }
    
    public void CheckAndSubmitPrice() {
    	Date dt = new Date();
    	DateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
    	String dtstr = format.format(dt);

    	Map<String, Float> submitSkuMap = new HashMap<String, Float>();
		for(Entry<String, MyPriceInfo> entry : mapMyPriceInfo.entrySet()){
			String asin = entry.getKey();
			Float newMyPrice = CheckStrategy(asin, entry.getValue());
			if ( null != newMyPrice )
			{
				MyLog.log.log(Level.INFO, "Submit price:" + asin + ":" + newMyPrice);
				submitSkuMap.put(entry.getValue().sellerSKU, newMyPrice);
			}
		}
		
		if ( !submitSkuMap.isEmpty() ) {
			String xml = BuildSubmitPriceXml(submitSkuMap);
			MyLog.log.log(Level.INFO, "Submit XML:\n" + xml);
			
			String xmlFilename = AutoSubmitPriceConfig.submitPriceFile + "SubmitPrice_" + dtstr + ".xml";
			try {
				FileWriter fileWriter = new FileWriter(xmlFilename, false);
				fileWriter.write(xml);
		    	fileWriter.flush();
		     	fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			if ( true ) {
				SubmitPriceFromFile(xmlFilename);
			}
		}
		else {
			MyLog.log.log(Level.INFO, "No need submit!!!!");
		}
    }
    
    
    public void SubmitPriceFromFile(String xmlFilename) {
    	MyLog.log.log(Level.INFO, "Submit XML file:" + xmlFilename);
		SubmitFeedRequest request = new SubmitFeedRequest();
        request.setMerchant(AutoSubmitPriceConfig.sellerId);
        request.setMWSAuthToken(AutoSubmitPriceConfig.sellerDevAuthToken);
        request.setMarketplaceIdList(new IdList(Arrays.asList(
        		AutoSubmitPriceConfig.marketplaceId)));

        request.setFeedType("_POST_PRODUCT_PRICING_DATA_");
        try {
        	FileInputStream fis = new FileInputStream(xmlFilename);
			request.setFeedContent( fis );
			String contentMd5 = computeContentMD5HeaderValue( fis );
			fis.getChannel().position( 0 );
	        request.setContentMD5( contentMd5 );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		catch( Throwable e ) {
			e.printStackTrace();
			return;
        }
    	SubmitPriceFeed(AutoSubmitPriceConfig.getServiceClient(), request);
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
    		MyLog.log.log(Level.WARNING, "InitConf error:" + confFileName);
    		return;
    	}

    	MyLog.log.info(AutoSubmitPriceConfig.toTxt());
    	
    	AutoSubmitPriceApp handler = new AutoSubmitPriceApp();
    	
    	
    	if ( (args.length == 2) && args[1].equals("1") )
    	{
    		handler.queryProductPrice(); 
    		handler.logPrice2File();
    	}
    	else if ( (args.length == 3) && args[1].equals("20") ) {
    		handler.queryProductPrice(); 
    		handler.SubmitPriceFromFile(args[2]);
    	}
    	else if ( (args.length == 2) && args[1].equals("21") ) {
    		handler.queryProductPrice();
    		handler.CheckAndSubmitPrice();
    	}
    	else if ( (args.length == 2) && args[1].equals("22") ) {
    		handler.queryProductPrice();
    		handler.logPrice2File();
    		handler.CheckAndSubmitPrice();
    	}
    	else {
    		String usage = "\n Usage configfile [params] \n"
    				+ "                  [1]      	      Only update price to history_price.txt \n"
    				+ "                  [20 xmlfile]      submit price from xml file \n"
    				+ "                  [21]              Auto get price and submit price by strategy \n"
    				+ "                  [22]              log to histtory_price.txt and submit price by strategy \n";
    		
    		System.out.print(usage);
    		MyLog.log.log(Level.WARNING, usage);
    	}
    }
}
