package counter;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class Driver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		
		//createSequenceFile();
		/*
		Path ip = new Path("/home/kaushikp/eclipse-workspace/MR_Part5/SequenceFileInputFormat/Hadoop_MR/Hadoop_MR/src/seqinput");
		Path op = new Path("/home/kaushikp/eclipse-workspace/MR_Part5/SequenceFileInputFormat/Hadoop_MR/Hadoop_MR/src/output");
		*/
		Job job = Job.getInstance();
		
		job.setJarByClass(Map.class);
		
		job.setInputFormatClass(SequenceFileInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		job.setMapperClass(Map.class);
		job.setCombinerClass(reducer.class);
		job.setReducerClass(reducer.class);
		
		job.setNumReduceTasks(2);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
		if(job.isSuccessful()) {
			System.out.println("File Created...!");
		}else if(!job.isSuccessful()) {
            System.out.println("Job was not successful");          
        }
		
		
	}
	
	
	public static void createSequenceFile() throws IOException {
		
        Configuration conf = new Configuration();
        Path inputFile = new Path("/home/kaushikp/eclipse-workspace/MR_Part5/SequenceFileInputFormat/Hadoop_MR/Hadoop_MR/src/input/access.log");
		Path outputFile = new Path("/home/kaushikp/eclipse-workspace/MR_Part5/SequenceFileInputFormat/Hadoop_MR/Hadoop_MR/src/seqinput");
		
        FileSystem fs = FileSystem.get(conf);
        FSDataInputStream inputStream = fs.open(inputFile);
        
        IntWritable key = new IntWritable();
        int count = 0;
        Text value = new Text();    
        String str;
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,outputFile, key.getClass(), value.getClass());
        while (inputStream.available() > 0) {
            key.set(count++);
            str = inputStream.readLine();
            value.set(str);
            writer.append(key, value);
        }
        fs.close();
        IOUtils.closeStream(writer);
        System.out.println("SEQUENCE FILE CREATED SUCCESSFULLY........");
	
	
	
	}

}
