package Count_required_moving_average;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

public class CountCrimeTypeYear implements WritableComparable<CountCrimeTypeYear>{

	String PrimaryType;
	Integer year;
	
	/*public CountByPrimaryTypeYear()
	{
		super();
	}
	
	public CountByPrimaryTypeYear(String pri, Integer n)
	{
		super();
		PrimaryType = pri;
		year = n;
	}
	*/
	public String getPrimaryType() {
		return PrimaryType;
	}

	public void setPrimaryType(String primaryType) {
		PrimaryType = primaryType;
	}

	public int getYear() {
		return year;
	}
	

	public void setYear(int year) {
		this.year = year;
	}

	
	@Override
	public void readFields(DataInput input) throws IOException {
		// TODO Auto-generated method stub
		PrimaryType = input.readUTF();
		year = input.readInt();
	}

	@Override
	public void write(DataOutput output) throws IOException {
		// TODO Auto-generated method stub
		output.writeUTF(PrimaryType);
		output.writeInt(year);
	}

	@Override
	public String toString() {
		return PrimaryType + "," + year+",";
	}

	@Override
	public int compareTo(CountCrimeTypeYear o) {
		// TODO Auto-generated method stub
		if(PrimaryType.compareTo(o.getPrimaryType()) == 0) {
			return year.compareTo(o.getYear());
		}
		//else {
			return PrimaryType.compareTo(o.getPrimaryType());
		//}
	}
	
}
