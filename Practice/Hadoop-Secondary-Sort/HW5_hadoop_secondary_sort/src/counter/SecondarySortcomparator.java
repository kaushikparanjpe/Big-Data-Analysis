package counter;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class SecondarySortcomparator extends WritableComparator {

	public SecondarySortcomparator() {
		super(Compositekeywritable.class, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		// TODO Auto-generated method stub
		Compositekeywritable cw1 = (Compositekeywritable) a;
		Compositekeywritable cw2 = (Compositekeywritable) b;
		
		int comResult = cw1.getAccessdate().compareTo(cw2.getAccessdate());
		if(comResult==0)
		{
			return -cw1.getIP().compareTo(cw2.getIP());
		}
		return comResult;
		
	}

	
}
