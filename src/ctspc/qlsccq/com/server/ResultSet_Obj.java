package ctspc.qlsccq.com.server;

import java.sql.ResultSet;
import java.sql.SQLException;

import ctspc.qlsccq.com.shared.FeedItem;
import ctspc.qlsccq.com.shared.Obj_SU_CO;
import ctspc.qlsccq.com.shared.Obj_TRAM;
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Obj_donvi;

public class ResultSet_Obj {

	// TRAM
	public static Obj_TRAM set_result_TRAM(ResultSet rs) {
		Obj_TRAM mDV = new Obj_TRAM();
		try {
			mDV.setMA_TRAM(rs.getString(Obj_TRAM.tag_MA_TRAM));
			mDV.setTEN_TRAM(rs.getString(Obj_TRAM.tag_TEN_TRAM));
			mDV.setLOAI_TRAM(rs.getString(Obj_TRAM.tag_LOAI_TRAM));
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mDV;

	}

	// TRU
	public static Obj_TRU set_result_TRU(ResultSet rs) {
		Obj_TRU oTRU = new Obj_TRU();
		try {
			oTRU.setTUYEN(rs.getString(Obj_TRU.tag_TUYEN));
			oTRU.setTRU(rs.getString(Obj_TRU.tag_TRU));
			oTRU.setX(rs.getString(Obj_TRU.tag_X));
			oTRU.setY(rs.getString(Obj_TRU.tag_Y));
			oTRU.setMANGXONG(rs.getString(Obj_TRU.tag_MANGXONG));
			oTRU.setNHANH_RE(rs.getString(Obj_TRU.tag_NHANH_RE));
			oTRU.setCACHTRAM(rs.getInt(Obj_TRU.tag_CACHTRAM));
			oTRU.setGHI_CHU(rs.getString(Obj_TRU.tag_GHI_CHU));
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

}
