package ctspc.qlsccq.com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.DB_CONFIG;
import ctspc.qlsccq.com.shared.DB_SQL;
import ctspc.qlsccq.com.shared.Obj_SOI;
import ctspc.qlsccq.com.shared.Obj_SU_CO;
import ctspc.qlsccq.com.shared.Obj_TRAM;
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Obj_donvi;
import ctspc.qlsccq.com.shared.ObjectClient;
import ctspc.qlsccq.com.shared.Utils;

@SuppressWarnings("serial")
public class Android_DSMBA_Servlet extends HttpServlet {
	String json = "first error";

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		CallbackResult mCB = new CallbackResult();
		DataInputStream is = new DataInputStream(req.getInputStream());
		String android = Function.byte_to_String(is);
		JsonParser jp = new JsonParser();
		JsonObject mJO = jp.parse(android).getAsJsonObject();
		ObjectClient mCL = M_READ_JSON.read_client(mJO);
		String lenh = "Chua xac dinh";
		if (mCL != null) {
			try {
				lenh = mCL.getCommand();
				mCB = greetServer();
				json = Utils.CB_OK;
			} catch (Exception e) {

			}
		}
		DataOutputStream dos = new DataOutputStream(res.getOutputStream());
		mCB.setCommand(lenh);
		mCB.setResultString(json);
		String kq = Function.alldata2server(mCB,null);
		Function.write_String_to_byte(dos, kq);
		dos.flush();
		dos.close();
	}

	public static void write_String_to_byte(DataOutputStream dos, String json) {
		byte[] data;
		try {
			data = json.getBytes("UTF-8");
			try {
				dos.writeInt(data.length);
				dos.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public CallbackResult greetServer() throws IllegalArgumentException {
		CallbackResult mCB = new CallbackResult();
		List<Obj_TRU> list_tru = null;
		List<Obj_TRAM> list_TRAM = null;
		List<Obj_TUYEN> list_TUYEN = null;
		List<Obj_SU_CO> list_SUCO = null;
		List<Obj_donvi> list_DONVI = null;
		List<Obj_SOI> list_SOI = null;

		Connection con =null;
		ResultSet rs_tram = null;
		Statement st_tram = null;
		ResultSet rs_tuyen = null;
		Statement st_tuyen = null;
		ResultSet rs_tru = null;
		Statement st_tru = null;
		ResultSet rs_suco = null;
		Statement st_suco = null;
		ResultSet rs_donvi = null;
		Statement st_donvi = null;
		ResultSet rs_soi = null;
		Statement st_soi = null;
		try {
			con = getDBConnection();
			
			st_donvi = con.createStatement();
			rs_donvi = st_donvi.executeQuery(DB_SQL.SQL_GET_DONVI);
			if (rs_donvi != null) {
				list_DONVI = new ArrayList<Obj_donvi>();
				while (rs_donvi.next()) {
					Obj_donvi oDONVI = ResultSet_Obj.set_result_DONVI(rs_donvi);
					list_DONVI.add(oDONVI);
				}
			}
			mCB.setList_DONVI(list_DONVI);

			st_tram = con.createStatement();
			rs_tram = st_tram.executeQuery(DB_SQL.SQL_GET_TRAM);
			if (rs_tram != null) {
				list_TRAM = new ArrayList<Obj_TRAM>();
				while (rs_tram.next()) {
					Obj_TRAM oTRAM = ResultSet_Obj.set_result_TRAM(rs_tram);
					list_TRAM.add(oTRAM);
				}
			}
			mCB.setList_tram(list_TRAM);
			
			st_tuyen = con.createStatement();
			rs_tuyen = st_tuyen.executeQuery(DB_SQL.SQL_GET_TUYEN);
			if (rs_tuyen != null) {
				list_TUYEN = new ArrayList<Obj_TUYEN>();
				while (rs_tuyen.next()) {
					Obj_TUYEN oTUYEN = ResultSet_Obj.set_result_TUYEN(rs_tuyen);
					list_TUYEN.add(oTUYEN);
				}
			}
			mCB.setList_tuyen(list_TUYEN);

			st_tru = con.createStatement();
			rs_tru = st_tru.executeQuery(DB_SQL.SQL_GET_TRU);
			if (rs_tru != null) {
				list_tru = new ArrayList<Obj_TRU>();
				while (rs_tru.next()) {
					Obj_TRU mMBA = ResultSet_Obj.set_result_TRU(rs_tru);
					list_tru.add(mMBA);
				}
			}
			mCB.setList_tru(list_tru);
			
			// su co
			st_suco = con.createStatement();
			rs_suco = st_suco.executeQuery(DB_SQL.SQL_GET_SUCO);
			if (rs_suco != null) {
				list_SUCO = new ArrayList<Obj_SU_CO>();
				while (rs_suco.next()) {
					Obj_SU_CO mMBA = ResultSet_Obj.set_result_SUCO(rs_suco);
					list_SUCO.add(mMBA);
				}
			}
			mCB.setList_suco(list_SUCO);
			
			// soi
			st_soi = con.createStatement();
			rs_soi = st_soi.executeQuery(DB_SQL.SQL_GET_SOI);
			if (rs_soi != null) {
				list_SOI = new ArrayList<Obj_SOI>();
				while (rs_soi.next()) {
					Obj_SOI mMBA = ResultSet_Obj.set_result_SOI(rs_soi);
					list_SOI.add(mMBA);
				}
			}
			mCB.setList_SOI(list_SOI);
			
		} catch (Exception e) {
			json = e.toString();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
				if (st_tram != null) {
					st_tram.close();
				}
				if (rs_tram != null) {
					rs_tram.close();
				}
				if (st_tuyen != null) {
					st_tuyen.close();
				}
				if (rs_tuyen != null) {
					rs_tuyen.close();
				}
				if (st_tru != null) {
					st_tru.close();
				}
				if (rs_tru != null) {
					rs_tru.close();
				}
				if (st_suco != null) {
					st_suco.close();
				}
				if (rs_suco != null) {
					rs_suco.close();
				}
				if (st_donvi != null) {
					st_donvi.close();
				}
				if (rs_donvi != null) {
					rs_donvi.close();
				}
				if (st_soi != null) {
					st_soi.close();
				}
				if (rs_soi != null) {
					rs_soi.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mCB;
	}

	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection("jdbc:oracle:thin:@"
					+ DB_CONFIG.IP + ":1521:" + DB_CONFIG.DATA_NAME,
					DB_CONFIG.DATA_USER, DB_CONFIG.DATA_PASS);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;

	}
}
