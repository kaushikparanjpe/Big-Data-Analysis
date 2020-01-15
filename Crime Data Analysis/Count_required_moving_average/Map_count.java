package Count_required_moving_average;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map_count extends Mapper<LongWritable, Text, CountCrimeTypeYear, IntWritable> {

	
	final static IntWritable NUMONE = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, CountCrimeTypeYear, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
			String line = value.toString();
			String crimeType = line.split(",")[5];
			int year = Integer.parseInt(line.split(",")[17]);
			
			CountCrimeTypeYear mapOutput = new CountCrimeTypeYear();
			mapOutput.setPrimaryType(crimeType);
			mapOutput.setYear(year);
			
			context.write(mapOutput, NUMONE);	
		
	}
	
}
