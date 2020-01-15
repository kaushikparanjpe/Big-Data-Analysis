package merge;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper <LongWritable, Text, Text, DoubleWritable> {

	private final static DoubleWritable one = new DoubleWritable();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		
		
		if (key.get() == 0){
		return;
		}
		else
		{
			String field = value.toString();
			String[] container = field.split("\\r?\\n");
			
			for (String c : container)
			{
				Text t = new Text(c.split(",")[1]);
				one.set(Double.parseDouble(c.split(",")[4]));
				context.write(t, one);
			}
			
		}
		
	}

	
}
