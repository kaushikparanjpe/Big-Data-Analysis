package MovingAVERAGE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class movingAVG_reducer extends Reducer<Text, Movingavg__data, Text, Text>{

	private int windowSize = 3;
	
	@Override
	protected void reduce(Text key, Iterable<Movingavg__data> values,
			Reducer<Text, Movingavg__data, Text, Text>.Context context) throws IOException, InterruptedException {
	
		List<Movingavg__data>sortedTimeSeries = new ArrayList<>();
		for(Movingavg__data t : values) {
			//System.out.println("a:"+key.toString() + " b:" + t.getYear()+ " c:"+t.getCount());
			Movingavg__data temp = new Movingavg__data();
			temp.setCount(t.getCount());
			temp.setYear(t.getYear());
			sortedTimeSeries.add(temp);
			
		}
		Collections.sort(sortedTimeSeries);
		
		//List<TimeSeriesData>sortedTimeSeries = StreamSupport.stream(values.spliterator(), false).collect(Collectors.toList());
		for(Movingavg__data t : sortedTimeSeries) {
			System.out.println("a:"+key.toString() + " b:" + t.getYear()+ " c:"+t.getCount());	
		}
		// TODO Auto-generated method stub
		
		double sum =0;
		/*
		for (int i=0; i < windowSize-1; i++) {
			sum += sortedTimeSeries.get(i).getCount();        
		}*/
		   
		/*for (int i = windowSize-1; i < sortedTimeSeries.size(); i++) {
			
			sum += sortedTimeSeries.get(i).getCount();
			double movingAverage = sum / windowSize;
			long timestamp = sortedTimeSeries.get(i).getYear();
			Text outputValue = new Text(timestamp+ "," + movingAverage);
			//System.out.println(timestamp+ "," + movingAverage);
			context.write(key, outputValue);
			sum -= sortedTimeSeries.get(i-windowSize+1).getCount();
		
		}*/
		
		for(int i = 0; i < sortedTimeSeries.size(); i++) {
			if(i < windowSize-1) {
				sum += sortedTimeSeries.get(i).getCount();
				double movingAverage = sum / (i+1);
				long timestamp = sortedTimeSeries.get(i).getYear();
				Text outputValue = new Text(timestamp+ "," + movingAverage);
				String l = key.toString()+",";
				context.write(new Text(l), outputValue);
			}else {
				sum += sortedTimeSeries.get(i).getCount();
				double movingAverage = sum / windowSize;
				long timestamp = sortedTimeSeries.get(i).getYear();
				Text outputValue = new Text(timestamp+ "," + movingAverage);
				String l = key.toString()+",";
				context.write(new Text(l), outputValue);
				sum -= sortedTimeSeries.get(i-windowSize +1).getCount();
			}
			
		}
		sortedTimeSeries.clear();
	}
		
}