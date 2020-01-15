package counter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper <Object, Text, Compositekeywritable, NullWritable> {

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Compositekeywritable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		
		String field = value.toString();
		String[] container = field.split("\\r?\\n");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy':'HH:mm:ssZ");
        Date date = new Date();
        Date date2 = new Date();
        
		for (String l: container)
		{		
			String t = (l.split(" - - ")[0]);
			try {
	            date = formatter.parse(l.split("[\\[\\]]")[1].replaceAll("Z$", "+0000"));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			String m  = date.toString();
			
			SimpleDateFormat formatter1 = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
			try {
				 date2 = (Date)formatter1.parse(m);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(date2);
			String formatedDate =  cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +   cal.get(Calendar.DATE)      ;
			
			Compositekeywritable cw = new Compositekeywritable(formatedDate,t);
			context.write(cw, NullWritable.get());

		}		
	}

	
}
