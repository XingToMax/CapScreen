package org.nuaa.tomax.csreen;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2018/5/28 14:40
 */
public class LoadProperties {
    private static Properties prop  = null;

    static {
        prop = new Properties();
        try {
            prop.load(new FileInputStream(LoadProperties.class.getResource("/").getPath()+"setting.properties"));
            System.out.println("加载成功");
        } catch (IOException e) {
            System.out.println("未找到文件");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String getString(String key){
        if (prop.containsKey(key)){
            return (String) prop.get(key);
        }
        return "";
    }

    public static List<String> getStringList(String key){
        List<String> stringList = new ArrayList<>();
        if (prop.containsKey(key)){
            String val = (String) prop.get(key);
            val = val.replace("[","");
            val = val.replace("]","");
            String [] ans = val.split(",");
            for (String in : ans){
                stringList.add(in);
            }
        }
        return stringList;
    }

    public static String getSavePath(){
        return getString("savePath");
    }

    public static List<String> getLabels(){
        return getStringList("labels");
    }

    public static void main(String [] args){
        System.out.println(getSavePath());
        List<String> labels = getLabels();
        for (String in : labels){
            System.out.println(in);
        }
    }
}
