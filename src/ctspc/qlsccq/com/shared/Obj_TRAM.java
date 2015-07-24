package ctspc.qlsccq.com.shared;

import java.io.Serializable;

public class Obj_TRAM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String MA_TRAM;
	public String TEN_TRAM;
	public String LOAI_TRAM;
	public String USER_TAO;
	public String USER_SUA;
	
	public static final String tag_TABLE_TRAM="SC_TRAM";
	public static final String tag_MA_TRAM="MA_TRAM";
	public static final String tag_TEN_TRAM="TEN_TRAM";
	public static final String tag_LOAI_TRAM="LOAI_TRAM";
	public static final String tag_USER_TAO="USER_TAO";
	public static final String tag_USER_SUA="USER_SUA";
	
	
	public String getUSER_TAO() {
		return USER_TAO;
	}
	public void setUSER_TAO(String uSER_TAO) {
		USER_TAO = uSER_TAO;
	}
	public String getUSER_SUA() {
		return USER_SUA;
	}
	public void setUSER_SUA(String uSER_SUA) {
		USER_SUA = uSER_SUA;
	}
	public String getMA_TRAM() {
		return MA_TRAM;
	}
	public void setMA_TRAM(String mA_TRAM) {
		MA_TRAM = mA_TRAM;
	}
	public String getTEN_TRAM() {
		return TEN_TRAM;
	}
	public void setTEN_TRAM(String tEN_TRAM) {
		TEN_TRAM = tEN_TRAM;
	}
	public String getLOAI_TRAM() {
		return LOAI_TRAM;
	}
	public void setLOAI_TRAM(String lOAI_TRAM) {
		LOAI_TRAM = lOAI_TRAM;
	}
	
	public String getLOAI_TRAM_label() {
		String KQ = getLOAI_TRAM();
		try {
			for (Obj_Text oT : Utils.get_list_LOAITRAM()) {
				if(oT.KEY.equals(KQ)){
					KQ = oT.NAME;
				}
			}
		} catch (Exception e) {
			
		}
		return KQ;
	}
	
}
