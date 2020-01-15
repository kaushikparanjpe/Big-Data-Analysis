package Count_required_moving_average;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce_count extends Reducer<CountCrimeTypeYear, IntWritable, CountCrimeTypeYear, IntWritable>{

	private IntWritable total = new IntWritable();
	@Override
	protected void reduce(CountCrimeTypeYear key, Iterable<IntWritable> values,
			Reducer<CountCrimeTypeYear, IntWritable, CountCrimeTypeYear, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int count = 0;	
		for(IntWritable val : values) {
			count+=val.get();
		}
		total.set(count);
		context.write(key, total);
	}

	
}
