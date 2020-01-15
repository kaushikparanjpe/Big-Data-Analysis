package CT_Aresst_counts;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

public class Count_crimetype_arrest implements WritableComparable<Count_crimetype_arrest>{

	String PrimaryType;
	String Arrest;
	
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

	public String getArrest() {
		return Arrest;
	}
	

	public void setArrest(String year) {
		this.Arrest = year;
	}

	
	@Override
	public void readFields(DataInput input) throws IOException {
		// TODO Auto-generated method stub
		PrimaryType = input.readUTF();
		Arrest = input.readUTF();
	}

	@Override
	public void write(DataOutput output) throws IOException {
		// TODO Auto-generated method stub
		output.writeUTF(PrimaryType);
		output.writeUTF(Arrest);
	}

	@Override
	public String toString() {
		return PrimaryType + "," + Arrest+",";
	}

	@Override
	public int compareTo(Count_crimetype_arrest o) {
		// TODO Auto-generated method stub
		if(PrimaryType.compareTo(o.getPrimaryType()) == 0) {
			return Arrest.compareTo(o.getArrest());
		}
		//else {
			return PrimaryType.compareTo(o.getPrimaryType());
		//}
	}
	
}
