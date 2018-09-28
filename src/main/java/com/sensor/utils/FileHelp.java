package com.sensor.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileHelp {

	
public List<Object> fileList(File dir) throws Exception{
	List<Object> list =new ArrayList<>();
	int count=0 ;
	// 返回当前目录所包含的所有的文件和子目录。
			File [] files = dir.listFiles();		
			for(File file: files){				
				HashMap map = new HashMap<>();	        	 
 	        	 List<Object> list2 = new ArrayList<>();
				 // 如果是目录
		    	  if(file.isHidden()==false&&file.isDirectory()){
		  	    	map.put("id",file.getName());
		  	    	map.put("name",file.getName());
		  	    	//System.out.println(file.getName());
		  	    	list.add(map);
		  	           File [] files2 =  file.listFiles(new FileFilter() {

						@Override
						public boolean accept(File pathname) {
							//以txt结尾，
							try {
								if(pathname.getCanonicalPath().endsWith(".json"))
								{
									return true ;
									}
							} catch (IOException e) {
								e.printStackTrace();
							}
							return false;
						}});
		  	          
		  	           for(int j =0 ;j<files2.length;j++){
		  	        	 HashMap map2 = new HashMap<>();
		  	             map2.put("id", j);
		  	             map2.put("name", files2[j].getName());
		  	             map2.put("cid", files2[j].getParentFile().getName());
		  	             list2.add(map2);
		  	           map.put("json", list2);
		  	        	   }
		  	        // System.out.println(list2);			  	        
		  	    	}
		    
			}
		    
	return list;
}

}
