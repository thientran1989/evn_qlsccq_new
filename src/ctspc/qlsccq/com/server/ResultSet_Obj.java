package ctspc.qlsccq.com.server;

import java.sql.ResultSet;
import java.sql.SQLException;




import ctspc.qlsccq.com.shared.FeedItem;
import ctspc.qlsccq.com.shared.Obj_SOI;
import ctspc.qlsccq.com.shared.Obj_SU_CO;
import ctspc.qlsccq.com.shared.Obj_TRAM;
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Obj_User;
import ctspc.qlsccq.com.shared.Obj_donvi;

public class ResultSet_Obj {

	// TRAM
	public static Obj_TRAM set_result_TRAM(ResultSet rs) {
		Obj_TRAM mDV = new Obj_TRAM();
		try {
			mDV.setMA_TRAM(rs.getString(Obj_TRAM.tag_MA_TRAM));
			mDV.setTEN_TRAM(rs.getString(Obj_TRAM.tag_TEN_TRAM));
			mDV.setLOAI_TRAM(rs.getString(Obj_TRAM.tag_LOAI_TRAM));
			mDV.setUSER_TAO(rs.getString(Obj_TRAM.tag_USER_TAO));
			mDV.setUSER_SUA(rs.getString(Obj_TRAM.tag_USER_SUA));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mDV;

	}
	// TUYEN
	public static Obj_TUYEN set_result_TUYEN(ResultSet rs) {
		Obj_TUYEN mDV = new Obj_TUYEN();
		try {
			mDV.setMA_TUYEN(rs.getString(Obj_TUYEN.tag_MA_TUYEN));
			mDV.setTEN_TUYEN(rs.getString(Obj_TUYEN.tag_TEN_TUYEN));
			mDV.setTRAM_DAU(rs.getString(Obj_TUYEN.tag_TRAM_DAU));
			mDV.setTRAM_CUOI(rs.getString(Obj_TUYEN.tag_TRAM_CUOI));
			mDV.setLOAI_CAP(rs.getString(Obj_TUYEN.tag_LOAI_CAP));
			mDV.setCHIEU_DAI(rs.getString(Obj_TUYEN.tag_CHIEU_DAI));
			mDV.setTT_SOI(rs.getString(Obj_TUYEN.tag_TT_SOI));
			mDV.setUSER_TAO(rs.getString(Obj_TRAM.tag_USER_TAO));
			mDV.setUSER_SUA(rs.getString(Obj_TRAM.tag_USER_SUA));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mDV;

	}
	// SOI
	public static Obj_SOI set_result_SOI(ResultSet rs) {
		Obj_SOI oSOI = new Obj_SOI();
		try {
			oSOI.setTUYEN(rs.getString(Obj_SOI.tag_TUYEN));
			oSOI.setSOI(rs.getInt(Obj_SOI.tag_SOI));
			oSOI.setTT_SOI(rs.getString(Obj_SOI.tag_TT_SOI));
			oSOI.setGHI_CHU(rs.getString(Obj_SOI.tag_GHI_CHU));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oSOI;

	}
	// TRU
	public static Obj_TRU set_result_TRU(ResultSet rs) {
		Obj_TRU oTRU = new Obj_TRU();
		try {
			oTRU.setTRU(rs.getString(Obj_TRU.tag_TRU));
			oTRU.setX(rs.getString(Obj_TRU.tag_X));
			oTRU.setY(rs.getString(Obj_TRU.tag_Y));
			oTRU.setMANGXONG(rs.getString(Obj_TRU.tag_MANGXONG));
			oTRU.setNHANH_RE(rs.getString(Obj_TRU.tag_NHANH_RE));
			oTRU.setCACHTRAM(rs.getInt(Obj_TRU.tag_CACHTRAM));
			oTRU.setGHI_CHU(rs.getString(Obj_TRU.tag_GHI_CHU));
			oTRU.setMA_DVI(rs.getString(Obj_TRU.tag_MA_DVI));
			oTRU.setUSER_TAO(rs.getString(Obj_TRAM.tag_USER_TAO));
			oTRU.setUSER_SUA(rs.getString(Obj_TRAM.tag_USER_SUA));
		} catch (Exception e) {

		}
		return oTRU;
	}

	// SU CO
	public static Obj_SU_CO set_result_SUCO(ResultSet rs) {
		Obj_SU_CO oSC = new Obj_SU_CO();
		try {
			oSC.setTIME_PHATHIEN(rs.getString(Obj_SU_CO.tag_TIME_PHATHIEN));
			oSC.setTIME_XULY(rs.getString(Obj_SU_CO.tag_TIME_XULY));
			oSC.setNOI_DUNG(rs.getString(Obj_SU_CO.tag_NOI_DUNG));
			oSC.setNGUYEN_NHAN(rs.getString(Obj_SU_CO.tag_NGUYEN_NHAN));
			oSC.setKHAC_PHUC(rs.getString(Obj_SU_CO.tag_KHAC_PHUC));
			oSC.setPHAM_VI(rs.getString(Obj_SU_CO.tag_PHAM_VI));
			oSC.setDON_VI(rs.getString(Obj_SU_CO.tag_DON_VI));
			oSC.setTRU(rs.getString(Obj_SU_CO.tag_TRU));
			oSC.setSO_QD(rs.getString(Obj_SU_CO.tag_SO_QD));
			oSC.setUSER_TAO(rs.getString(Obj_TRAM.tag_USER_TAO));
			oSC.setUSER_SUA(rs.getString(Obj_TRAM.tag_USER_SUA));
			try {
				oSC.setTG_PHATHIEN(rs.getTimestamp(Obj_SU_CO.tag_TG_PHATHIEN));
			} catch (Exception e) {
			}
			try {
				oSC.setTG_XULY(rs.getTimestamp(Obj_SU_CO.tag_TG_XULY));
			} catch (Exception e) {
				
			}
//			oSC.setTIME_PHATHIEN_LABEL();
//			oSC.setTIME_XULY_LABEL();
			
		} catch (Exception e) {

		}
		return oSC;
	}
	// 
	public static Obj_donvi set_result_DONVI (ResultSet rs){
		Obj_donvi mDV = new Obj_donvi();
		try {
			mDV.setMa_donvi(rs.getString(Obj_donvi.TAG_ma_donvi+""));
			mDV.setTen_donvi(rs.getString(Obj_donvi.TAG_ten_donvi+""));
			mDV.setX_chuan(rs.getString(Obj_donvi.TAG_x_chuan+""));
			mDV.setY_chuan(rs.getString(Obj_donvi.TAG_y_chuan+""));
			mDV.setX_tam(rs.getString(Obj_donvi.TAG_x_tam+""));
			mDV.setY_tam(rs.getString(Obj_donvi.TAG_y_tam+""));
			mDV.setCap(rs.getString(Obj_donvi.TAG_cap+""));
			mDV.setTen_full(rs.getString(Obj_donvi.TAG_ten_full+""));
			mDV.setTen_rutgon(rs.getString(Obj_donvi.TAG_ten_rutgon+""));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mDV;
		
	}
	// FEED ITEM
	public static FeedItem set_result_FeedItem (ResultSet rs){
		FeedItem mFI = new FeedItem();
		try {
			mFI.setId(rs.getInt(FeedItem.tag_id));
			mFI.setName(rs.getString(FeedItem.tag_name));
			mFI.setImge(rs.getString(FeedItem.tag_image));
			mFI.setStatus(rs.getString(FeedItem.tag_status));
			mFI.setProfilePic(rs.getString(FeedItem.tag_profilePic));
			mFI.setTimeStamp(rs.getString(FeedItem.tag_timeStamp));
			mFI.setUrl(rs.getString(FeedItem.tag_url));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mFI;
		
	}
	// USER
	public static Obj_User set_result_USER (ResultSet rs){
		Obj_User mUS = new Obj_User();
		try {
			mUS.setMa_donvi(rs.getString(Obj_User.TAG_ma_donvi));
			mUS.setPassword(rs.getString(Obj_User.TAG_password));
			mUS.setTen_nhanvien(rs.getString(Obj_User.TAG_ten_nhanvien));
			mUS.setUsername_mba(rs.getString(Obj_User.TAG_username_sc));
			mUS.setMa_bophan(rs.getString(Obj_User.TAG_ma_bophan));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mUS;
		
	}

}
