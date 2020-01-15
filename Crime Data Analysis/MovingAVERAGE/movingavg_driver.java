package MovingAVERAGE;

import java.io.IOException;

import org.apache.hadoop.fs.Path;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class movingavg_driver {

	public static void main(String[] args) {
	
		try {
			Job job = Job.getInstance();
			
			job.setJarByClass(movingAVG_map.class);
			job.setInputFormatClass(TextInputFormat.class);
			
			
			job.setMapperClass(movingAVG_map.class);
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Movingavg__data.class);
			
			job.setReducerClass(movingAVG_reducer.class);
			
			
			job.setNumReduceTasks(1);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
			
			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			
			System.exit(job.waitForCompletion(true) ? 0 : 1);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
