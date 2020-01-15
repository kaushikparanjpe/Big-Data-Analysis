package DistrictCodeJoin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JoinDriver {
	//public static HashMap<String, String> countsData = new HashMap<>();
	//public static Text outval = new Text();
	public static void main(String[] args) {
		
		try {
			Configuration conf = new Configuration();
			//conf.addResource("/usr/local/bin/hadoop-2.7.3/etc/hadoop/core-site.xml");
			//conf.addResource("/usr/local/bin/hadoop-2.7.3/etc/hadoop/hdfs-site.xml");			
			//FileSystem fs = FileSystem.get(conf);
			//URI uri = URI.create("hdfs://localhost:9000/");
			//Path p = new Path(uri + "/outf/mydata.txt");
			conf.set("join.type", "inner");
			//conf.set("file.loc", args[1]);
			Job job = Job.getInstance(conf);
			job.setJarByClass(DistrictJoin.class);
			job.setJobName("Replicate Join");
			//fs.open(p).readl;
			
			
			job.setMapperClass(DistrictJoin.class);
			job.setInputFormatClass(TextInputFormat.class);
			job.addCacheFile(new Path(args[1]).toUri());
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(NullWritable.class);
			
			
			
//			BufferedReader br = new BufferedReader(new FileReader("/Users/prathmesh/Downloads/FinalProject/output/mydata.txt"));
//			String line;
//			while((line = br.readLine())!=null) {
//				System.out.println(line);
//				String crimeType = line.split(",")[0];
//				String year = line.split(",")[1];
//				String count = line.split(",")[2];
//				//String count = "1";
//				String key = crimeType + ":" + year;
//				countsData.put(key, count);
//			}
//			System.out.println(JoinDriver.countsData.toString());

			
			job.setNumReduceTasks(0);
			//job.setOutputKeyClass(Text.class);
			//job.setOutputValueClass(NullWritable.class);
			
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[2]));
			
			//FileSystem fs = FileSystem.get(URI.create("hdfs://localhost:9000/prathmesh/hdfs/mydata.txt"), conf);
			
			System.exit(job.waitForCompletion(true) ? 0 : 1);
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
