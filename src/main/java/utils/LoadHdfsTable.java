package utils;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class LoadHdfsTable {
    public static String appTab = "C:\\Users\\lenovo\\Desktop\\文档\\output\\appTab.txt";
    private static Map<String, String[]> appMap = new HashMap<>();

    static {
        try {
            StringBuffer sb = new StringBuffer();
            String line;
            BufferedReader bf = ReadHdfsFile.fileReader(appTab);
            while ((line = bf.readLine()) != null){
                String[] appArray = line.split(PropertiesUtil.getProperty("Separator"));
                sb.append(appArray[1]).append(",");
                sb.append(appArray[2]).append(",").append(appArray[3]).append(",");
                sb.append(appArray[4]).append(",").append(appArray[5]).append(",").append(appArray[6]).append(",");
                sb.append(appArray[7]).append(",").append(appArray[8]);

                String[] appToValue = sb.toString().split(",");
                appMap.put(appArray[0], appToValue);
                sb.delete(0, sb.length());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Map<String, String[]> getAppMap(){
        return appMap;
    }

}
