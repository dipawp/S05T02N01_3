package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {
	@Value("${cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.timezone")
	private String tIMEZONE;

	private SimpleDateFormat simpleDateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone(tIMEZONE));
		return sdf;
	}
	
	
	public String getDateString() {
		Date now = new Date();
		return simpleDateFormat().format(now);
	}
	
	public long getDateMillis() {
		Date now = new Date();
		String strDate = simpleDateFormat().format(now);
		Date strNow = new Date();
		try {
			strNow = simpleDateFormat().parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strNow.getTime();
	}
}
