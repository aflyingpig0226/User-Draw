package userMR.UserMR1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import utils.PropertiesUtil;
import utils.TextArrayWritable;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class UserDrawMapper1 extends Mapper<LongWritable, Text, Text, TextArrayWritable> {
    Text key2 = new Text();
    @Override
    protected void map(LongWritable key1, Text value1, Context context) throws IOException, InterruptedException {
        String line = value1.toString();
        String[] dataArray = line.split(PropertiesUtil.getProperty("Separator"));
        String uiqkey = dataArray[Integer.parseInt(PropertiesUtil.getProperty("MDN"))] +
                dataArray[Integer.parseInt(PropertiesUtil.getProperty("appID"))];
        String timeNow = dataArray[Integer.parseInt(PropertiesUtil.getProperty("Date"))];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        String val[] = new String[5];
        val[0] = sdf.format(Long.parseLong(timeNow));
        val[1] = dataArray[Integer.parseInt(PropertiesUtil.getProperty("MDN"))];
        val[2] = dataArray[Integer.parseInt(PropertiesUtil.getProperty("appID"))];
        val[3] = PropertiesUtil.getProperty("count");
        val[4] = dataArray[Integer.parseInt(PropertiesUtil.getProperty("ProcedureTime"))];
        key2.set(uiqkey);
        context.write(key2, new TextArrayWritable(val));
    }
}
