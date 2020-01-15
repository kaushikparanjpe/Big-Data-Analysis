package CT_Aresst_counts;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class reduce extends Reducer<Count_crimetype_arrest, IntWritable, Count_crimetype_arrest, IntWritable>{

	private IntWritable total = new IntWritable();
	@Override
	protected void reduce(Count_crimetype_arrest key, Iterable<IntWritable> values,
			Reducer<Count_crimetype_arrest, IntWritable, Count_crimetype_arrest, IntWritable>.Context context)
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
