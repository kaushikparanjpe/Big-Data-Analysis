package counter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper <LongWritable, BytesWritable, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	

	@Override
	protected void map(LongWritable key, BytesWritable value, Mapper<LongWritable, BytesWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
				
		String field = new String(value.getBytes());
		String[] container = field.split("\\r?\\n");
		
		for (String l: container)
		{
			Text t = new Text(l.split(" ")[0]);
			context.write(t, one);

		}	
		
	}

	
}
