package com.sensor.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class HttpUtils {

	public static String convertStreamToString(InputStream is)
	{
	
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is ,"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }     
       // return  JSON.Encode(JSON.Decode(sb.toString()));
        return sb.toString();
        
	}
	public static String getResponse(String url , String httpHeaderName , String HttpHeaderValue){
		
		// 创建HttpClient实例     
        HttpClient httpclient = new DefaultHttpClient();  
        String name =httpHeaderName ;
        String  value =HttpHeaderValue ;
        String str = "" ;
        try {
			url = URLDecoder.decode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
        // 创建Get方法实例     
       HttpGet httpgets = new HttpGet(url); 
      //  System.out.println("name"+name);
      httpgets.addHeader(name, value);
    	HttpResponse response;
		try {
			response = httpclient.execute(httpgets);
			HttpEntity entity = response.getEntity(); 
			 if (entity != null) {    
		            InputStream instreams = entity.getContent();   
			
		             str = convertStreamToString(instreams);  
		            //System.out.println("Do something");   
		            //System.out.println(str);    
		            httpgets.abort();    
		            }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}    
        
        return str ;
	}
}


