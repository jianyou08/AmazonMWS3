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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
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
public class MyLog {
    public static Logger log; 
    
    private static FileHandler fileHandler;

    private static MarketplaceWebServiceProductsAsyncClient client = null;
    
    public static int InitLog(String logfileName) {
    	log = Logger.getLogger("amazon-mws"); 
    	log.setLevel(Level.INFO);
		try {
			fileHandler = new FileHandler(logfileName, true);
			fileHandler.setLevel(Level.INFO); 
	        fileHandler.setFormatter(new MyLogHander()); 
	        log.addHandler(fileHandler); 
		} catch (SecurityException e) {
			e.printStackTrace();
			return -2;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
    	return 0;
    }
}
    
class MyLogHander extends Formatter { 
    @SuppressWarnings("deprecation")
	@Override 
    public String format(LogRecord record) { 
    	Date dt = new Date(record.getMillis());
        return "[" + dt.toString() + "]:" + record.getSourceClassName() + ":" + record.getSourceMethodName() + ":" + record.getMessage()+"\n"; 
    } 
} 

