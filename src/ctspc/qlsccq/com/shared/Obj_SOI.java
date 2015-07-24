package ctspc.qlsccq.com.shared;

import java.io.Serializable;

public class Obj_SOI implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public String TUYEN;
	public int SOI;
	public String TT_SOI;
	public String GHI_CHU;
	
	public static final String tag_TABLE ="SC_SOI";
	public static final String tag_TUYEN ="TUYEN";
	public static final  String tag_SOI ="SOI";
	public static final  String tag_TT_SOI ="TT_SOI";
	public static final  String tag_GHI_CHU ="GHI_CHU";
	
	public String getTUYEN() {
		return TUYEN;
	}
	public void setTUYEN(String tUYEN) {
		TUYEN = tUYEN;
	}
	public int getSOI() {
		return SOI;
	}
	public void setSOI(int sOI) {
		SOI = sOI;
	}
	public String getTT_SOI() {
		return TT_SOI;
	}
	public void setTT_SOI(String tT_SOI) {
		TT_SOI = tT_SOI;
	}
	public String getGHI_CHU() {
		return GHI_CHU;
	}
	public void setGHI_CHU(String gHI_CHU) {
		GHI_CHU = gHI_CHU;
	}
	
	
}
