package ss;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {


	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		// 月初
		System.out.println("月初" + getMonthStart());
		// 月末
		System.out.println("月末" + getMonthEnd());
		String s="2015-02-01 02:03:04";
		System.out.println(isCurrentMonth(sdf.parse(s)));
		Date date = sdf.parse(getMonthEnd());
		System.out.println(date);
		int sa = s.length();
		String x="12345";
		System.out.println(x.length());
		System.out.println(x.substring(2,5));
		String dd = s.substring(sa-4, sa);
		System.out.println(dd);
		
	}
	private static String isCurrentMonth(Date date) {
		String inMonth=null;
		Calendar c=Calendar.getInstance();
		c.setTime(new Date());
		int month1=c.get(Calendar.MONTH)+1;
		int year1 =c.get(Calendar.YEAR);
		c.setTime(date);
		int month2=c.get(Calendar.MONTH)+1;
		int year2 =c.get(Calendar.YEAR);
		if(year1==year2){
			if(month1==month2){
				inMonth="本月";
			}else{
				inMonth=month2+"月";
			}
		}else{
			inMonth=year2+"年"+month2+"月";
		}
		return inMonth;
	}

	private static String getMonthStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int index = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DATE, (1 - index));
		return sdf.format(calendar.getTime())+" 00:00:00";
	}

	private static String getMonthEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return sdf.format(calendar.getTime())+" 00:00:00";
	}

	private static Date getNext(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	


}
