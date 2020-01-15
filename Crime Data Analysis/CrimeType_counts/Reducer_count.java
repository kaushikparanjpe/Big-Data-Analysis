package CrimeType_counts;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reducer_count extends Reducer<Text, IntWritable, Text, IntWritable>{

	private IntWritable total = new IntWritable();
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int count = 0;	
		for(IntWritable val : values) {
			count+=val.get();
		}
		total.set(count);
		String l = key.toString()+",";
		context.write(new Text(l), total);
	}

	
}
