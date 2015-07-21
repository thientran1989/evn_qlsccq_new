package ctspc.qlsccq.com.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class Client_function {
	
	public static java.sql.Timestamp date2timestamp(Date date){
		return (new java.sql.Timestamp(date.getTime())) ;
	}
	public static java.sql.Date date2sqldate(Date date){
		return (new java.sql.Date(date.getTime())) ;
	}
	public static java.sql.Date timestamp2sqldate(java.sql.Timestamp timestamp){
		return (new java.sql.Date(timestamp.getTime())) ;
	}
	public static java.util.Date timestamp2date(java.sql.Timestamp timestamp){
		return (new java.util.Date(timestamp.getTime())) ;
	}
	public static String timestamp2string(java.sql.Timestamp timestamp){
		String dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(new java.util.Date(timestamp.getTime()));
		return dateString;
	}
	public static String date2string(Date mytime){
		String dateString="dd/MM/yyyy";
		try {
			dateString = DateTimeFormat.getFormat("dd/MM/yyyy").format(mytime);
		} catch (Exception e) {
			
		}
		return dateString;
	}

}
