package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadHdfsFile {
    public static BufferedReader fileReader(String fileName) throws Exception{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C:\\Users\\lenovo\\Desktop\\文档\\output\\appTab.txt")));
        return bufferedReader;

        /**
         * 集群写法
         Configuration conf = new Configuration();
         FileSystem fs = FileSystem.get(conf);
         FSDataInputStream in = fs.open(new Path(fileName));
         BufferedReader br = new BufferedReader(new InputStreamReader(in));
         return br;
         */

    }
}
