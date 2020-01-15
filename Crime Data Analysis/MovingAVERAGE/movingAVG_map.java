package MovingAVERAGE;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class movingAVG_map extends Mapper<LongWritable, Text, Text, Movingavg__data>{

	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Movingavg__data>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		String crimeType = line.split(",")[0];
		int year = Integer.parseInt(line.split(",")[1].trim());
		int count = Integer.parseInt(line.split(",")[2].trim());
		Movingavg__data data = new Movingavg__data();
		data.setYear(year);
		data.setCount(count);
		
		//System.out.println("Key:" + crimeType + " Year:"+data.getYear() + " Count:" + data.getCount());
		context.write(new Text(crimeType), data);
		
	}

}