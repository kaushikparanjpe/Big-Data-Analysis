package customWritable;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<Text, MinMaxDateClosePrice, Text, MinMaxDateClosePrice>{

	private MinMaxDateClosePrice result = new MinMaxDateClosePrice();

	@Override
	protected void reduce(Text key, Iterable<MinMaxDateClosePrice> values,
			Reducer<Text, MinMaxDateClosePrice, Text, MinMaxDateClosePrice>.Context context)
			throws IOException, InterruptedException {
		
		result.setMaxDate(null);
		result.setMinDate(null);
		result.setMax_stock_volume(0);
		result.setMax_stock_price_adj(0);
		
		for(MinMaxDateClosePrice val : values) {
		
			

			if(result.getMax_stock_price_adj() < val.getMax_stock_price_adj()) {
				result.setMax_stock_price_adj(val.getMax_stock_price_adj());
			}
			
			
			
			if(result.getMax_stock_volume() < val.getMax_stock_volume()) {
				result.setMaxDate(val.getMaxDate());
				result.setMax_stock_volume(val.getMax_stock_volume());
			}else {
				result.setMinDate(val.getMinDate());
			}
			
			
		
			
		}
		
		context.write(key, result);
	}
	
	
}
