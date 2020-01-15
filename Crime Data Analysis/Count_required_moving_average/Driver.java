package Count_required_moving_average;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Job job = Job.getInstance();
			
			job.setJarByClass(Map_count.class);
			job.setInputFormatClass(TextInputFormat.class);
			
			
			job.setMapperClass(Map_count.class);
			job.setMapOutputKeyClass(CountCrimeTypeYear.class);
			job.setMapOutputValueClass(IntWritable.class);
			
			job.setReducerClass(Reduce_count.class);
			
			
			job.setNumReduceTasks(1);
			job.setOutputKeyClass(CountCrimeTypeYear.class);
			job.setOutputValueClass(IntWritable.class);
			
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			System.exit(job.waitForCompletion(true) ? 0 : 1);
			
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
