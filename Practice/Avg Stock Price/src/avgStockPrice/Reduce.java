package avgStockPrice;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<YearStockSymbol, OutputTuple, YearStockSymbol, OutputTuple>{

	public OutputTuple  result  = new OutputTuple();
	@Override
	protected void reduce(YearStockSymbol key, Iterable<OutputTuple> values,
			Reducer<YearStockSymbol, OutputTuple, YearStockSymbol, OutputTuple>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		float sum = 0;
		int count = 0;
	
		for(OutputTuple ot : values) {
			sum += ot.getCount() * ot.getAvg_stock_price();
			count += ot.getCount();
		}
		
		result.setCount(count);
		result.setAvg_stock_price(sum / count);
		
		context.write(key, result);
	}

	
}
