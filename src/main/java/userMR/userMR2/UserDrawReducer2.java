package userMR.userMR2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import userDraw.UserDraw;
import utils.LoadHdfsTable;
import utils.PropertiesUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserDrawReducer2 extends Reducer<Text, Text, Text, Text> {
    Map<String, String[]> appMap = LoadHdfsTable.getAppMap();
    Text value4 = new Text();
    @Override
    protected void reduce(Text key2, Iterable<Text> values2, Context context) throws IOException, InterruptedException {
        HashMap<String, UserDraw> userDrawMap = new HashMap<>();
        Set<String> keySet = userDrawMap.keySet();
        String keyMDN;

        for (Text v : values2) {
            String[] dataArray = v.toString().split(PropertiesUtil.getProperty("Separator"));
            keyMDN = dataArray[1];
            String appID = dataArray[2];

            if (appID.length() > 0){
                if (appMap.get(appID) == null){
                    continue;
                }
                float male = Float.parseFloat(appMap.get(appID)[1]);
                float female = Float.parseFloat(appMap.get(appID)[2]);
                float age1 = Float.parseFloat(appMap.get(appID)[3]);
                float age2 = Float.parseFloat(appMap.get(appID)[4]);
                float age3 = Float.parseFloat(appMap.get(appID)[5]);
                float age4 = Float.parseFloat(appMap.get(appID)[6]);
                float age5 = Float.parseFloat(appMap.get(appID)[7]);

                long times = Long.parseLong(dataArray[4]);
                if (userDrawMap.containsKey(keyMDN) == true){
                    UserDraw userDraw = userDrawMap.get(keyMDN);
                    userDraw.protraitSex(male, female, times);
                    userDraw.protraitAge(age1, age2, age3, age4, age5, times);
                }else{
                    userDrawMap.put(keyMDN, createDrawData(dataArray, male, female, age1, age2, age3, age4, age5, times));
                }
            }
        }

        for (String keys : keySet) {
            value4.set(userDrawMap.get(keys).toString());
            context.write(null, value4);
        }
    }

    private static UserDraw createDrawData(String[] dataArray, float male, float female, float age1, float age2, float age3, float age4, float age5, long times) {
        UserDraw userDraw = new UserDraw();
        userDraw.setStartTimeDay(dataArray[0]);
        userDraw.setMDN(dataArray[1]);

        userDraw.initAge(age1, age2, age3, age4, age5);
        userDraw.initSex(male, female);

        return userDraw;
    }
}
