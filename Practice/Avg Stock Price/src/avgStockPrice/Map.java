package avgStockPrice;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, YearStockSymbol, OutputTuple>{

	YearStockSymbol tuple  = new YearStockSymbol();
	OutputTuple stock_price = new OutputTuple();
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, YearStockSymbol, OutputTuple>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String line = value.toString();
		
		String yr = line.split(",")[2].split("-")[0];
		String stock_symbol = line.split(",")[1];
		float stock_price_adj = Float.parseFloat(line.split(",")[8]);
		
		tuple.setStock_symbol(stock_symbol);
		tuple.setYear(yr);
		stock_price.setAvg_stock_price(stock_price_adj);
		stock_price.setCount(1);
		
		context.write(tuple, stock_price);
	}

	
	
}
