package org.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties {
    public static Properties properties;
    static{
        try{
            properties=new Properties();
            InputStream input=TestProperties.class.getResourceAsStream("info.properties");
            properties.load(input);
            input.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String last=getLastModify();
        String str=update("2014-09-05");
        System.out.println(last);
        System.out.println(getLastModify());
    }
    public static String getLastModify(){
            String last=    (String) properties.get("lastModify");
            return last;
    }
    public static String update(String lastModify){
        try{
        FileOutputStream out=new FileOutputStream(new File(TestProperties.class.getResource("info.properties").toURI()));
//       String str= (String) 
    		   properties.setProperty("lastModify",lastModify);  
        properties.store(out, "Update lastModifyTime");  
        out.close();  
        return "";
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
         
    }
 
}
