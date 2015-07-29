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

import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;

import javax.xml.datatype.XMLGregorianCalendar;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;

import com.amazonservices.mws.client.*;
import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;
import com.amazonservices.mws.products.samples.GetCompetitivePricingForASINSample;
import com.amazonservices.mws.products.samples.GetMyPriceForASINSample;
import com.amazonservices.mws.products.samples.ListMatchingProductsSample;


/** Sample call for GetMatchingProduct. */
public class  GetCompetitivePrice {
	private class ProductPrice {
		public Float competitivePrice;
		public Float myPrice;
		public ProductPrice(float _cptPrice, float _myPrice) {
			competitivePrice = _cptPrice;
			myPrice = _myPrice;
		}
	}
	private Map<String, ProductPrice> mapProductPrice = new HashMap<String, ProductPrice>();
    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
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
            		System.out.println("Cpt:" + asin + ':' + price);
            		MyLog.log.info("Cpt:" + asin + ':' + price);
            		mapProductPrice.put(asin, new ProductPrice(price, 0));
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
            		String asin = resItem.getASIN();
            		Float price = offer.getRegularPrice().getAmount().floatValue();
            		System.out.println("My:" + asin + ':' + price);
            		MyLog.log.info("My:" + asin + ':' + price);
            		
            		ProductPrice priceObj = mapProductPrice.get(asin);
            		priceObj.myPrice = price;
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
    
    public void AppendToHistoryFile(String filenaem) {
    	FileWriter fileWriter;
		try {
			long timestamp = System.currentTimeMillis();
			Date dt = new Date();
			fileWriter = new FileWriter(filenaem, true);
			for(Entry<String, ProductPrice> entry : mapProductPrice.entrySet()){ 
				String line;
				line = timestamp + "," + entry.getKey() + ',' + entry.getValue().competitivePrice + "," + entry.getValue().myPrice + "," + dt.toString();
				fileWriter.write(line + "\r\n"); 

				if ( entry.getValue().competitivePrice != entry.getValue().myPrice ) {
					MyLog.log.info("Adjust Price:" + line);
				}
				else 
				{
					MyLog.log.info("Normal:" + line);
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
    	String confFileName = "./auto_update_price.xml";
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
    	GetCompetitivePrice handler = new GetCompetitivePrice();
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
    	
    	handler.AppendToHistoryFile(AutoSubmitPriceConfig.historyPriceFile);
    }
}
