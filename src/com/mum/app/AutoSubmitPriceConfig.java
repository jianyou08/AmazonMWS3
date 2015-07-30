/******************************************************************************* 
 *  Copyright 2009 Amazon Services.
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  
 *  You may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at: http://aws.amazon.com/apache2.0
 *  This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR 
 *  CONDITIONS OF ANY KIND, either express or implied. See the License for the 
 *  specific language governing permissions and limitations under the License.
 * ***************************************************************************** 
 *
 *  Marketplace Web Service Java Library
 *  API Version: 2009-01-01
 *  Generated: Wed Feb 18 13:28:48 PST 2009 
 * 
 */

package com.mum.app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import javax.swing.text.Document;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException;  
import java.io.PrintWriter; 
import javax.xml.parsers.ParserConfigurationException; 
import javax.xml.transform.OutputKeys; 
import javax.xml.transform.Transformer; 
import javax.xml.transform.TransformerConfigurationException; 
import javax.xml.transform.TransformerException; 
import javax.xml.transform.TransformerFactory; 
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 
import org.w3c.dom.Element; 
import org.w3c.dom.Node; 
import org.w3c.dom.NodeList; 
import org.xml.sax.SAXException; 

import com.amazonaws.mws.*;
import com.amazonaws.mws.model.*;
import com.amazonservices.mws.client.MwsXmlReader;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsAsyncClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsConfig;
import com.amazonaws.mws.mock.MarketplaceWebServiceMock;

/**
 * 
 * Submit Feed Samples
 * 
 * 
 */
public class AutoSubmitPriceConfig {
    public static String accessKey = "";
    public static String secretKey = "";
    public static String appName = "mumapp";
    public static String appVersion = "V0.10";
    public static String serviceURL = "";
    public static String sellerId = "";
    public static String sellerDevAuthToken = "";
    public static String marketplaceId = "";
    public static List<String> reqAsinList;
    public static String historyPriceFile = "";
    
    public static Logger log = Logger.getLogger("amazon-mws"); 
    

    private static MarketplaceWebServiceProductsAsyncClient productClient = null;
    private static MarketplaceWebServiceClient              mwsClient = null;
    
    public static int InitConfig(String fileName) {
    	log.setLevel(Level.INFO);
    	
		try {
			InputStream is = new FileInputStream(fileName);
    		@SuppressWarnings("resource")
			MwsXmlReader xmlReader = new MwsXmlReader(is);
    		
    		//account
    		accessKey = xmlReader.read("acct_accessKey", String.class);
    		secretKey = xmlReader.read("acct_secretKey", String.class);
    		appName = xmlReader.read("acct_appName", String.class);
    		appVersion = xmlReader.read("acct_appVersion", String.class);
    		serviceURL = xmlReader.read("acct_serviceURL", String.class);
    		sellerId = xmlReader.read("acct_sellerId", String.class);
    		sellerDevAuthToken = xmlReader.read("acct_mwsAuthToken", String.class);
    		marketplaceId = xmlReader.read("acct_marketplaceId", String.class);
    		
    		//asin list
    		reqAsinList = xmlReader.readList("RequestASINList", "ASIN", String.class);
    		
    		historyPriceFile = xmlReader.read("HistoryPriceFile", String.class);
    		
    		String logfileName = xmlReader.read("LogFile", String.class);
    		if ( MyLog.InitLog(logfileName) != 0 )
    		{
    			return -3;
    		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
    	return 0;
    }
    
    //Get Product client
    public static MarketplaceWebServiceProductsClient getProductClient() {
        return getAsyncProductClient();
    }

    public static synchronized MarketplaceWebServiceProductsAsyncClient getAsyncProductClient() {
        if (productClient==null) {
            MarketplaceWebServiceProductsConfig config = new MarketplaceWebServiceProductsConfig();
            config.setServiceURL(serviceURL);
            // Set other client connection configurations here.
            productClient = new MarketplaceWebServiceProductsAsyncClient(accessKey, secretKey, 
                    appName, appVersion, config, null);
        }
        return productClient;
    }
    
  //Get Product client
    public static MarketplaceWebService getServiceClient() {
        return getAsyncServiceClient();
    }

    public static synchronized MarketplaceWebServiceClient getAsyncServiceClient() {
        if (mwsClient==null) {
            MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
            config.setServiceURL(serviceURL);
            // Set other client connection configurations here.
            mwsClient = new MarketplaceWebServiceClient(
            		accessKey, secretKey, appName, appVersion, config);
        }
        return mwsClient;
    }

    public static String toTxt() {
    	String res = "";
    	res += "==========Req Config=========================";
        res += "\n accessKey            :" + accessKey;
        res += "\n secretKey            :" + secretKey;
        res += "\n appName              :" + appName;
        res += "\n appVersion           :" + appVersion;
        res += "\n serviceURL           :" + serviceURL;
        res += "\n sellerId             :" + sellerId;
        res += "\n sellerDevAuthToken   :" + sellerDevAuthToken;
        res += "\n marketplaceId        :" + marketplaceId;
        res += "\n historyPriceFile     :" + historyPriceFile;
        res += "\n RequestAsinList      :";
        for(String asin : reqAsinList) {
            res += "\n              asin:" + asin;
        }
        res += "\n==========End End End=========================";
    	return res;
    }
}
