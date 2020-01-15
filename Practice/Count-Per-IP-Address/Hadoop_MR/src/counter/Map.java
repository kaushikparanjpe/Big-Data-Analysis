package counter;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper <LongWritable, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		String field = value.toString();
		String[] container = field.split("\\r?\\n");
		
		for (String l: container)
		{
			Text t = new Text(l.split(" - - ")[0]);
			context.write(t, one);

		}		
	}

	
}
