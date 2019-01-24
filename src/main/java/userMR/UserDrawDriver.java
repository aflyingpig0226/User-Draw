package userMR;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import userMR.UserMR1.UserDrawMapper1;
import userMR.UserMR1.UserDrawReducer1;
import userMR.userMR2.UserDrawReducer2;
import userMR.userMR2.UserDrawMapper2;
import utils.TextArrayWritable;

public class UserDrawDriver {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job1 = Job.getInstance(conf);
        job1.setJarByClass(UserDrawDriver.class);

        job1.setMapperClass(UserDrawMapper1.class);
        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(TextArrayWritable.class);

        job1.setReducerClass(UserDrawReducer1.class);
        job1.setOutputKeyClass(Text.class);
        job1.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));

        boolean b1 = job1.waitForCompletion(true);
        System.out.println("job1执行成功！！！");
        if (b1){
            conf = new Configuration();
            Job job2 = Job.getInstance(conf);
            job2.setJarByClass(UserDrawDriver.class);

            job2.setMapperClass(UserDrawMapper2.class);
            job2.setMapOutputKeyClass(Text.class);
            job2.setMapOutputValueClass(Text.class);

            job2.setReducerClass(UserDrawReducer2.class);
            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(Text.class);

            FileInputFormat.addInputPath(job2, new Path(args[1]));
            FileOutputFormat.setOutputPath(job2, new Path(args[2]));
            boolean b2 = job2.waitForCompletion(true);
            System.out.println("job2执行成功！！！");

        }
    }
}
