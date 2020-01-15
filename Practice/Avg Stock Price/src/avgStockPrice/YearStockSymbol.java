package avgStockPrice;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class YearStockSymbol implements WritableComparable<YearStockSymbol>{
	
	String stock_symbol;
	String year;
	public String getStock_symbol() {
		return stock_symbol;
	}
	public void setStock_symbol(String stock_symbol) {
		this.stock_symbol = stock_symbol;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		stock_symbol = in.readUTF();
		year = in.readUTF();
	}
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(stock_symbol);
		out.writeUTF(year);
	}
	@Override
	public int compareTo(YearStockSymbol o) {
		// TODO Auto-generated method stub
		if(this.stock_symbol.compareTo(o.getStock_symbol()) == 0 ) {
			return this.year.compareTo(o.getYear());
		}else {
			return this.stock_symbol.compareTo(o.getStock_symbol());
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Keys:{stock_symbol:" + stock_symbol + ", year:" + year + "}";
	}

}
