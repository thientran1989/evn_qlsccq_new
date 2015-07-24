package ctspc.qlsccq.com.server;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ctspc.qlsccq.com.client.Client_function;
import ctspc.qlsccq.com.shared.Obj_SOI;
import ctspc.qlsccq.com.shared.Obj_SU_CO;
import ctspc.qlsccq.com.shared.Obj_TRAM;
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.Obj_TUYEN;

public class SQL_Obj {

	// TRAM
	public static String get_sql_insert_TRAM() {
		String insertTableSQL = "Insert into CSKH." + Obj_TRAM.tag_TABLE_TRAM
				+ " (";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_MA_TRAM + ",";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_TEN_TRAM + ",";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_LOAI_TRAM + ",";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_USER_TAO + ",";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_USER_SUA + "";
		insertTableSQL = insertTableSQL + ") Values " + "(?,?,?,?,?)";
		return insertTableSQL;
	}

	public static void set_preparedStatement_TRAM(
			PreparedStatement preparedStatement, Obj_TRAM oTRAM) {
		try {
			preparedStatement.setString(1, oTRAM.getMA_TRAM());
			preparedStatement.setString(2, oTRAM.getTEN_TRAM());
			preparedStatement.setString(3, oTRAM.getLOAI_TRAM());
			preparedStatement.setString(4, oTRAM.getUSER_TAO());
			preparedStatement.setString(5, oTRAM.getUSER_SUA());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// TUYEN
	public static String get_sql_insert_TUYEN() {
		String insertTableSQL = "Insert into CSKH." + Obj_TUYEN.tag_TABLE_TUYEN
				+ " (";
		insertTableSQL = insertTableSQL + Obj_TUYEN.tag_MA_TUYEN + ",";
		insertTableSQL = insertTableSQL + Obj_TUYEN.tag_TEN_TUYEN + ",";
		insertTableSQL = insertTableSQL + Obj_TUYEN.tag_TRAM_DAU + ",";
		insertTableSQL = insertTableSQL + Obj_TUYEN.tag_TRAM_CUOI + ",";
		insertTableSQL = insertTableSQL + Obj_TUYEN.tag_LOAI_CAP + ",";
		insertTableSQL = insertTableSQL + Obj_TUYEN.tag_CHIEU_DAI + ",";
		insertTableSQL = insertTableSQL + Obj_TUYEN.tag_TT_SOI + ",";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_USER_TAO + ",";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_USER_SUA + "";
		insertTableSQL = insertTableSQL + ") Values " + "(?,?,?,?,?,?,?,?,?)";
		return insertTableSQL;
	}

	public static void set_preparedStatement_TUYEN(
			PreparedStatement preparedStatement, Obj_TUYEN oTUYEN) {
		try {
			preparedStatement.setString(1, oTUYEN.getMA_TUYEN());
			preparedStatement.setString(2, oTUYEN.getTEN_TUYEN());
			preparedStatement.setString(3, oTUYEN.getTRAM_DAU());
			preparedStatement.setString(4, oTUYEN.getTRAM_CUOI());
			preparedStatement.setString(5, oTUYEN.getLOAI_CAP());
			preparedStatement.setString(6, oTUYEN.getCHIEU_DAI());
			preparedStatement.setString(7, oTUYEN.getTT_SOI());
			preparedStatement.setString(8, oTUYEN.getUSER_TAO());
			preparedStatement.setString(9, oTUYEN.getUSER_SUA());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// SOI
	public static String get_sql_insert_SOI() {
		String insertTableSQL = "Insert into CSKH." + Obj_SOI.tag_TABLE
				+ " (";
		insertTableSQL = insertTableSQL + Obj_SOI.tag_TUYEN + ",";
		insertTableSQL = insertTableSQL + Obj_SOI.tag_SOI + ",";
		insertTableSQL = insertTableSQL + Obj_SOI.tag_TT_SOI + ",";
		insertTableSQL = insertTableSQL + Obj_SOI.tag_GHI_CHU + "";
		insertTableSQL = insertTableSQL + ") Values " + "(?,?,?,?)";
		return insertTableSQL;
	}

	public static void set_preparedStatement_SOI(
			PreparedStatement preparedStatement, Obj_SOI oTUYEN) {
		try {
			preparedStatement.setString(1, oTUYEN.getTUYEN());
			preparedStatement.setInt(2, oTUYEN.getSOI());
			preparedStatement.setString(3, oTUYEN.getTT_SOI());
			preparedStatement.setString(4, oTUYEN.getGHI_CHU());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// TRU
	public static String get_sql_insert_TRU() {
		String insertTableSQL = "Insert into CSKH." + Obj_TRU.tag_TABLE + " (";
		insertTableSQL = insertTableSQL + Obj_TRU.tag_TRU + ",";
		insertTableSQL = insertTableSQL + Obj_TRU.tag_X + ",";
		insertTableSQL = insertTableSQL + Obj_TRU.tag_Y + ",";
		insertTableSQL = insertTableSQL + Obj_TRU.tag_MANGXONG + ",";
		insertTableSQL = insertTableSQL + Obj_TRU.tag_NHANH_RE + ",";
		insertTableSQL = insertTableSQL + Obj_TRU.tag_CACHTRAM + ",";
		insertTableSQL = insertTableSQL + Obj_TRU.tag_GHI_CHU + ",";
		insertTableSQL = insertTableSQL + Obj_TRU.tag_TG_TAO + ",";
		insertTableSQL = insertTableSQL + Obj_TRU.tag_MA_DVI + ",";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_USER_TAO + ",";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_USER_SUA + "";
		insertTableSQL = insertTableSQL + ") Values " + "(?,?,?,?,?,?,?,?,?,?,?)";
		return insertTableSQL;
	}

	public static void set_preparedStatement_TRU(
			PreparedStatement preparedStatement, Obj_TRU oTRU) {
		try {
			preparedStatement.setString(1, oTRU.getTRU());
			preparedStatement.setString(2, oTRU.getX());
			preparedStatement.setString(3, oTRU.getY());
			preparedStatement.setString(4, oTRU.getMANGXONG());
			preparedStatement.setString(5, oTRU.getNHANH_RE());
			preparedStatement.setInt(6, oTRU.getCACHTRAM());
			preparedStatement.setString(7, oTRU.getGHI_CHU());
			preparedStatement.setTimestamp(8, getCurrentTimeStamp());
			preparedStatement.setString(9, oTRU.getMA_DVI());
			preparedStatement.setString(10, oTRU.getUSER_TAO());
			preparedStatement.setString(11, oTRU.getUSER_SUA());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// SU CO

	public static String get_sql_insert_SUCO() {
		String insertTableSQL = "Insert into CSKH." + Obj_SU_CO.tag_TABLE
				+ " (";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_TIME_PHATHIEN + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_TG_PHATHIEN + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_TIME_XULY + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_TG_XULY + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_NOI_DUNG + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_NGUYEN_NHAN + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_KHAC_PHUC + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_PHAM_VI + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_DON_VI + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_TRU + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_SO_QD + ",";
		insertTableSQL = insertTableSQL + Obj_SU_CO.tag_NGUYEN_NHAN_TTE + ",";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_USER_TAO + ",";
		insertTableSQL = insertTableSQL + Obj_TRAM.tag_USER_SUA + "";
		insertTableSQL = insertTableSQL + ") Values "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return insertTableSQL;
	}

	public static void set_preparedStatement_SUCO(
			PreparedStatement preparedStatement, Obj_SU_CO oSUCO) {
		try {
			preparedStatement.setString(1, oSUCO.getTIME_PHATHIEN());
			preparedStatement.setDate(2,Client_function.timestamp2sqldate(oSUCO.getTG_PHATHIEN()));
			preparedStatement.setString(3, oSUCO.getTIME_XULY());
			preparedStatement.setDate(4,Client_function.timestamp2sqldate(oSUCO.getTG_XULY()));
			preparedStatement.setString(5, oSUCO.getNOI_DUNG());
			preparedStatement.setString(6, oSUCO.getNGUYEN_NHAN());
			preparedStatement.setString(7, oSUCO.getKHAC_PHUC());
			preparedStatement.setString(8, oSUCO.getPHAM_VI());
			preparedStatement.setString(9, oSUCO.getDON_VI());
			preparedStatement.setString(10, oSUCO.getTRU());
			preparedStatement.setString(11, oSUCO.getSO_QD());
			preparedStatement.setString(12, oSUCO.getNGUYEN_NHAN_TTE());
			preparedStatement.setString(13, oSUCO.getUSER_TAO());
			preparedStatement.setString(14, oSUCO.getUSER_SUA());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

}
