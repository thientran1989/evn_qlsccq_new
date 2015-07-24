package ctspc.qlsccq.com.shared;


public class DB_SQL {
	
	public static String SQL_GET_TRAM = "select * from CSKH.SC_TRAM";
	public static String SQL_GET_DONVI = "select * from "+Obj_donvi.TAG_table_donvi + " order by "
			+ Obj_donvi.TAG_ma_donvi + " asc";
	public static String SQL_GET_TUYEN = "select * from CSKH.SC_TUYEN";
	public static String SQL_GET_TRU = "select * from CSKH.SC_TRU order by "+Obj_TRU.tag_TG_TAO+" desc";
	public static String SQL_GET_SUCO = "select * from CSKH.SC_SU_CO order by "+Obj_SU_CO.tag_TG_PHATHIEN+" desc";
	
	public static String get_SQL_DELETE_TRU(Obj_TRU mTRU){
		String SQL ="delete from CSKH."
				+ Obj_TRU.tag_TABLE+ " where " + Obj_TRU.tag_TRU + "='"
				+ mTRU.getTRU()+"'";
		return SQL;
	}
	
	public static String get_SQL_LOC_SU_CO(String from, String to){
		String SQL ="select * from CSKH.SC_SU_CO where TG_PHATHIEN >=TO_DATE('"+from+"','DD/MM/YYYY') and TG_PHATHIEN <=TO_DATE('"+to+"','DD/MM/YYYY')";
		return SQL;
	}
	public static String get_SQL_SOI_OF_TUYEN(Obj_TUYEN oTUYEN){
		String SQL ="select * from CSKH.SC_SOI where "+Obj_SOI.tag_TUYEN+" ='"+oTUYEN.getMA_TUYEN()+"'";
		return SQL;
	}
	
	
}
