package counter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class Compositekeywritable implements Writable, WritableComparable<Compositekeywritable>{
	
		private String Accessdate;
		private String IP;
		
		public Compositekeywritable() {
			super();
		}

		public Compositekeywritable(String accessdate, String iP) {
			super();
			Accessdate = accessdate;
			IP = iP;
		}

		public String getAccessdate() {
			return Accessdate;
		}

		public void setAccessdate(String accessdate) {
			Accessdate = accessdate;
		}

		public String getIP() {
			return IP;
		}

		public void setIP(String iP) {
			IP = iP;
		}

		
		public int compareTo(Compositekeywritable o) {
			// TODO Auto-generated method stub
			
			int result = Accessdate.compareTo(o.Accessdate);
			
			if (result ==0)
			{
				result = IP.compareTo(o.IP);
			}
			
			return result;
		}

		@Override
		public void readFields(DataInput arg0) throws IOException {
			// TODO Auto-generated method stub
			IP = arg0.readUTF();
			Accessdate = arg0.readUTF();
		}

		@Override
		public void write(DataOutput arg0) throws IOException {
			// TODO Auto-generated method stub
			arg0.writeUTF(IP);
			arg0.writeUTF(Accessdate);
			
		}

		@Override
		public String toString() {
			return "Compositekeywritable [Accessdate=" + Accessdate + ", IP=" + IP + "]";
		}
		
		

}
