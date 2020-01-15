package MovingAVERAGE;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class Movingavg__data implements WritableComparable<Movingavg__data>{

	int year;
	int count;
	
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		year = in.readInt();
		count = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeInt(year);
		out.writeInt(count);
		
	}

	@Override
	public int compareTo(Movingavg__data o) {
		// TODO Auto-generated method stub
		return Integer.valueOf(year).compareTo(o.year);
	}

}
