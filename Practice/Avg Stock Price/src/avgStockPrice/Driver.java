package avgStockPrice;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		Job job = Job.getInstance();
		
		job.setJarByClass(Map.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		//job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapperClass(Map.class);
		job.setMapOutputKeyClass(YearStockSymbol.class);
		job.setMapOutputValueClass(OutputTuple.class);
		
		job.setCombinerClass(Reduce.class);
		
		
		job.setReducerClass(Reduce.class);
		job.setNumReduceTasks(2);
		job.setOutputKeyClass(YearStockSymbol.class);
		job.setOutputValueClass(OutputTuple.class);
		
		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		
		System.exit(job.waitForCompletion(true) ? 0 : 1);

		
	}


}
