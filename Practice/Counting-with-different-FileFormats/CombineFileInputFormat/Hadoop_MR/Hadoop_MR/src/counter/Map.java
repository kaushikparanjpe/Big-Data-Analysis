package counter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper <LongWritable, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private ArrayList<String> hh = new ArrayList<String>();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String field = value.toString().split(" - - ")[0];
		hh.add(field);
		for (String l: hh)
		{
			Text t = new Text(l);
			context.write(t, one);

		}
		
		
		
	}

	
}
