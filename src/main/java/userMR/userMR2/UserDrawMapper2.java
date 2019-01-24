package userMR.userMR2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import utils.PropertiesUtil;

import java.io.IOException;

public class UserDrawMapper2 extends Mapper<LongWritable, Text, Text, Text> {
    Text key2 = new Text();
    @Override
    protected void map(LongWritable key1, Text value1, Context context) throws IOException, InterruptedException {
        String line = value1.toString();
        String[] dataArray = line.split(PropertiesUtil.getProperty("Separator"));
        String newkey = dataArray[1];
        key2.set(newkey);

        context.write(key2, value1);
    }
}
