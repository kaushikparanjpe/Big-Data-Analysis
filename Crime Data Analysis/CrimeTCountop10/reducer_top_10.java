package CrimeTCountop10;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class reducer_top_10 extends Reducer<NullWritable, Text, NullWritable, Text>{

	private Map<String, Long> hmap = new HashMap<>();
	@Override
	protected void reduce(NullWritable key, Iterable<Text> values,
			Reducer<NullWritable, Text, NullWritable, Text>.Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		for(Text t : values) {
			String details = t.toString();
			String crimeType = details.split(",")[0];
			long count = Long.parseLong(details.split(",")[1].trim());
			hmap.put(crimeType, count);
		}
		
	}
	@Override
	protected void cleanup(Reducer<NullWritable, Text, NullWritable, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		List<Entry<String, Long>> top10crimes = entriesSortedByValues(hmap).subList(0, 10);
		
		for(Entry<String, Long> e : top10crimes) {
			String val = e.getKey().toString() + ","+e.getValue();
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
