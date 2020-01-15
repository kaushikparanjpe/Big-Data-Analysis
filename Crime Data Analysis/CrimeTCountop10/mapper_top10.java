package CrimeTCountop10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class mapper_top10 extends Mapper<LongWritable, Text, NullWritable, Text>{

	private Map<Text,Long> crimesCounts = new HashMap<Text, Long>();
	 
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		String crimeType = line.split(",")[0].trim();
		Long count = Long.parseLong(line.split(",")[1].trim());
		
		crimesCounts.put(new Text(crimeType), count);
		
	}

	@Override
	protected void cleanup(Mapper<LongWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		int n =0;
		List<Entry<Text,Long>> crimesList = entriesSortedByValues(crimesCounts);
		if(crimesList.size() >= 10) {
			n=10;
		}else {
			n = crimesList.size();
		}
		
		List<Entry<Text,Long>> top10crimes = entriesSortedByValues(crimesCounts).subList(0, n);
		
		for(Entry<Text, Long> e : top10crimes) {
			String val = e.getKey().toString() + "," + e.getValue();
			context.write(NullWritable.get(), new Text(val));
		}
	}
	
	
	private <K,V extends Comparable<? super V>> 
    List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {
	
	List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());
	
	Collections.sort(sortedEntries, 
	    new Comparator<Entry<K,V>>() {
	        @Override
	        public int compare(Entry<K,V> e1, Entry<K,V> e2) {
	            return e2.getValue().compareTo(e1.getValue());
	        }
	    }
	);
	
	return sortedEntries;
	}

	
	
}
