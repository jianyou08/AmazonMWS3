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


import com.amazonservices.mws.client.AbstractMwsObject;
import com.amazonservices.mws.client.MwsReader;
import com.amazonservices.mws.client.MwsWriter;

public   class ProductStrategy extends AbstractMwsObject {
	public String asin = "";
	public Float  priceRangeMin = null;
	public Float  priceRangeMax = null;
	public Float  setDiffPrice  = null;
	public String cmpType = "Competitive";
	public String cmpPrice = "listing";
	public Float  condition_mypriceHigh = null;
	public Float  condition_mypriceLow  = null;
	
	public boolean isValid() {
		if ( asin.isEmpty() || (null == priceRangeMin) || (null == priceRangeMax) || (null == setDiffPrice) || (null == condition_mypriceHigh) || (null == condition_mypriceLow)) {
			return false;
		}
		return true;
	}
	
	public String toString() {
        String res = "\n ProductStrategy(" + isValid() + "){";
        res += "\n asin                  :" + asin;
        res += "\n priceRangeMin         :" + priceRangeMin;
        res += "\n priceRangeMax         :" + priceRangeMax;
        res += "\n setDiffPrice          :" + setDiffPrice;
        res += "\n cmpType               :" + cmpType;
        res += "\n cmpPrice              :" + cmpPrice;
        res += "\n condition_mypriceHigh :" + condition_mypriceHigh;
        res += "\n condition_mypriceLow  :" + condition_mypriceLow;
        res += "\n }";
        return res;
	}

	@Override
	public void readFragmentFrom(MwsReader xmlReader) {
		// TODO Auto-generated method stub
		asin = xmlReader.read("ASIN", String.class);
		priceRangeMin = xmlReader.read("PriceRangeMin", Float.class);
		priceRangeMax = xmlReader.read("PriceRangeMax", Float.class);
		setDiffPrice  = xmlReader.read("SetPriceDiff", Float.class);
		cmpType       = xmlReader.read("Cmp_Type", String.class).toLowerCase();
		cmpPrice       = xmlReader.read("Cmp_Price", String.class).toLowerCase();
		condition_mypriceHigh = xmlReader.read("Condition_MyPriceHigh", Float.class);
		condition_mypriceLow = xmlReader.read("Condition_MyPriceLow", Float.class);
	}

	@Override
	public void writeFragmentTo(MwsWriter arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void writeTo(MwsWriter arg0) {
		// TODO Auto-generated method stub
	}
}

