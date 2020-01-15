package Dostrict_count_top10;

import java.io.IOException;

import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Distcount_driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Job job = Job.getInstance();
			
			job.setJarByClass(Distcount_driver.class);
			job.setInputFormatClass(TextInputFormat.class);
			
			
			job.setMapperClass(DistCountMaper.class);
			job.setMapOutputKeyClass(NullWritable.class);
			job.setMapOutputValueClass(Text.class);
			
			job.setReducerClass(Distcount_reducer.class);
			
			
			job.setNumReduceTasks(1);
			job.setOutputKeyClass(NullWritable.class);
			job.setOutputValueClass(Text.class);
			
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			System.exit(job.waitForCompletion(true) ? 0 : 1);
			
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
