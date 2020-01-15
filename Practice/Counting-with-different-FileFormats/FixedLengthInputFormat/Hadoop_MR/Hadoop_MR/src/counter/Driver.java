package counter;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FixedLengthInputFormat;

import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Driver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		//final String FIXED_RECORD_LENGTH ="fixedlengthinputformat.record.length";
		//conf.setInt(FIXED_RECORD_LENGTH, 7);
		conf.setInt(FixedLengthInputFormat.FIXED_RECORD_LENGTH, 8);
		Job job = Job.getInstance(conf);
		FixedLengthInputFormat.setRecordLength(conf, 8);
		job.setJarByClass(Map.class);
		
		job.setInputFormatClass(FixedLengthInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapperClass(Map.class);
		job.setCombinerClass(reducer.class);
		job.setReducerClass(reducer.class);
		
		job.setNumReduceTasks(2);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);

		
	}

}
