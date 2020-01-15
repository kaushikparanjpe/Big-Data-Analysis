package counter;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class reducer extends Reducer<Compositekeywritable, NullWritable,Compositekeywritable, NullWritable> {

	//private IntWritable total = new IntWritable();
	@Override
	protected void reduce(Compositekeywritable key, Iterable<NullWritable> value,
			Reducer<Compositekeywritable, NullWritable, Compositekeywritable, NullWritable>.Context context) throws IOException, InterruptedException {

		//int sum=0;
		for (NullWritable val: value)
		{
			//sum += val.get();
			context.write(key, val);
		}
		//total.set(sum);
		
	}

	
}
