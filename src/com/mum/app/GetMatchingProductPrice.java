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
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

import com.amazonservices.mws.client.*;
import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;
import com.amazonservices.mws.products.samples.GetCompetitivePricingForASINSample;
import com.amazonservices.mws.products.samples.ListMatchingProductsSample;
import com.amazonservices.mws.products.samples.MarketplaceWebServiceProductsSampleConfig;


/** Sample call for GetMatchingProduct. */
public class GetMatchingProductPrice {

    /**
     * Call the service, log response and exceptions.
     *
     * @param client
     * @param request
     *
     * @return The response.
     */
    public static GetCompetitivePricingForASINResponse invokeGetCompetitivePricingForASIN(
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
            
            List<GetCompetitivePricingForASINResult> resList = response.getGetCompetitivePricingForASINResult();
            for (GetCompetitivePricingForASINResult resItem : resList)
            {
            	List<CompetitivePriceType> priceList = resItem.getProduct().getCompetitivePricing().getCompetitivePrices().getCompetitivePrice();
            	for (CompetitivePriceType priceItem : priceList)
            	{
            		System.out.println(resItem.getASIN() + ':' + priceItem.getPrice().getLandedPrice().getAmount().floatValue());
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

    /**
     *  Command line entry point.
     */
    public static void main(String[] args) {

        // Get a client connection.
        // Make sure you've set the variables in MarketplaceWebServiceProductsSampleConfig.
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getClient();

        // Create a request.
        GetCompetitivePricingForASINRequest request = new GetCompetitivePricingForASINRequest();
        String sellerId = "A1MZ1G2HEVXJ5F";
        request.setSellerId(sellerId);
        String mwsAuthToken = "amzn.mws.f4a764a0-f3a3-c93a-f7ad-1afd1da799be";
        request.setMWSAuthToken(mwsAuthToken);
        String marketplaceId = "ATVPDKIKX0DER";
        request.setMarketplaceId(marketplaceId);
        ASINListType asinList = new ASINListType();
        ArrayList<String> al = new ArrayList<String>();
        al.add("B00L21BZ14");
        al.add("B005LTC44K");
        al.add("B00KH8MG0M");
        asinList.setASIN(al);
        request.setASINList(asinList);

        // Make the call.
        GetMatchingProductPrice.invokeGetCompetitivePricingForASIN(client, request);

    }
}
