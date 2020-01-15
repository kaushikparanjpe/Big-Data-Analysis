package CrimeTCountop10;

import java.io.IOException;

import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DiverforTop_10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Job job = Job.getInstance();
			
			job.setJarByClass(DiverforTop_10.class);
			job.setInputFormatClass(TextInputFormat.class);
			
			
			job.setMapperClass(mapper_top10.class);
			job.setMapOutputKeyClass(NullWritable.class);
			job.setMapOutputValueClass(Text.class);
			
			job.setReducerClass(reducer_top_10.class);
			
			
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
