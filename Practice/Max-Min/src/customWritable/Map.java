package customWritable;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, Text, MinMaxDateClosePrice>{

	private Text stock = new Text();
	private MinMaxDateClosePrice tuple = new MinMaxDateClosePrice();
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, MinMaxDateClosePrice>.Context context)
			throws IOException, InterruptedException {
	
		String line = value.toString();
		String stock_symbol = line.split(",")[1];
		
		String dt = (line.split(",")[2]);
		long stock_vol = Long.parseLong(line.split(",")[7]);
		float stock_price_adj = Float.parseFloat(line.split(",")[8]);
		
		tuple.setMaxDate(dt);
		tuple.setMinDate(dt);
		tuple.setMax_stock_volume(stock_vol);
		tuple.setMax_stock_price_adj(stock_price_adj);
		stock.set(stock_symbol);
		
		context.write(stock, tuple);
		
		
	}
	
	
}
