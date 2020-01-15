package customWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

public class MinMaxDateClosePrice implements Writable{

	String maxDate;
	String minDate;
	long max_stock_volume;
	float max_stock_price_adj;
	
	
	public float getMax_stock_price_adj() {
		return max_stock_price_adj;
	}



	public void setMax_stock_price_adj(float max_stock_price_adj) {
		this.max_stock_price_adj = max_stock_price_adj;
	}



	public String getMaxDate() {
		return maxDate;
	}



	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}



	public String getMinDate() {
		return minDate;
	}



	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}



	public long getMax_stock_volume() {
		return max_stock_volume;
	}



	public void setMax_stock_volume(long max_stock_volume) {
		this.max_stock_volume = max_stock_volume;
	}



	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		
		minDate = in.readUTF(); 
		maxDate = in.readUTF(); 
		max_stock_volume = in.readLong();
		max_stock_price_adj = in.readFloat();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(maxDate);
		out.writeUTF(minDate);
		out.writeLong(max_stock_volume);
		out.writeFloat(max_stock_price_adj);
	}
	
	

	@Override
	public String toString() {
		return "MinMaxDateClosePrice [maxDate=" + maxDate + ", minDate=" + minDate + ", max_stock_volume="
				+ max_stock_volume + "]";
	}

}
