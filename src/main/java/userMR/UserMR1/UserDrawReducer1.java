package userMR.UserMR1;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import utils.TextArrayWritable;

import java.io.IOException;

public class UserDrawReducer1 extends Reducer<Text, TextArrayWritable, Text, Text> {
    Text value4 = new Text();
    @Override
    protected void reduce(Text key3, Iterable<TextArrayWritable> values3, Context context) throws IOException, InterruptedException {
        long sum = 0;
        int count = 0;
        String[] res = new String[5];
        boolean flag = true;

        for (TextArrayWritable v : values3) {
            String[] vals = v.toStrings();
            if (flag){
                res = vals;
            }
            if (vals[3] != null){
                count = count + 1;
            }
            if (vals[4] != null){
                sum += Long.valueOf(vals[4]);
            }
        }
        res[3] = String.valueOf(count);
        res[4] = String.valueOf(sum);

        StringBuffer sb = new StringBuffer();
        sb.append(res[0]).append("|");
        sb.append(res[1]).append("|");
        sb.append(res[2]).append("|");
        sb.append(res[3]).append("|");
        sb.append(res[4]);
        value4.set(sb.toString());

        context.write(null, value4);

    }
}
