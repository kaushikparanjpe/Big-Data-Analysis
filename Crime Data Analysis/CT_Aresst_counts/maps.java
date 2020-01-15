package CT_Aresst_counts;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class maps extends Mapper<LongWritable, Text, Count_crimetype_arrest, IntWritable> {

	
	final static IntWritable NUMONE = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Count_crimetype_arrest, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
			String line = value.toString();
			String crimeType = line.split(",")[5];
			String Arrest = line.split(",")[8];
			
			Count_crimetype_arrest mapOutput = new Count_crimetype_arrest();
			mapOutput.setPrimaryType(crimeType);
			mapOutput.setArrest(Arrest);
			
			context.write(mapOutput, NUMONE);	
		
	}
	
}
