package avgStockPrice;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class OutputTuple implements Writable{

	int count;
	float avg_stock_price;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getAvg_stock_price() {
		return avg_stock_price;
	}
	public void setAvg_stock_price(float avg_stock_price) {
		this.avg_stock_price = avg_stock_price;
	}
	
	
	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		count = in.readInt();
		avg_stock_price = in.readFloat();
	}
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(count);
		out.writeFloat(avg_stock_price);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Avg:" + avg_stock_price + "," + "Count:" + count + "]"; 
	}
	
}
