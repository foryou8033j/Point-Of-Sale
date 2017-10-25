package PointOfView.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CalendarWrappingAdapter extends XmlAdapter<String, Calendar> {

    @Override
    public Calendar unmarshal(String v) throws Exception {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREA);
	Date date = sdf.parse(v);// all done
	Calendar cal = sdf.getCalendar();
	
        return cal;
    }

    @Override
    public String marshal(Calendar v) throws Exception {
        return v.getInstance().toString();
    }
}