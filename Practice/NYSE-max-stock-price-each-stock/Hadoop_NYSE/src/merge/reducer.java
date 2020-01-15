package merge;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class reducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {

	private DoubleWritable total = new DoubleWritable();
	@Override
	protected void reduce(Text key, Iterable<DoubleWritable> value,
			Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {

		double sum=0.0;
		
		for (DoubleWritable val: value)
		{
			if (val.get()>sum)
			sum = val.get();
		}
		total.set(sum);
		context.write(key, total);
	}

	
}
