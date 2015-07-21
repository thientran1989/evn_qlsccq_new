package ctspc.qlsccq.com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.DB_CONFIG;
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.ObjectClient;
import ctspc.qlsccq.com.shared.Utils;

@SuppressWarnings("serial")
public class Android_InsertTRU_Servlet extends HttpServlet {
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
		mCB.setResultString(json);
		if (mCL != null) {
			try {
				lenh = mCL.getCommand();
				if (lenh.equals("themtru")) {
					Insert_TRU(mCL.getoTRU());
					mCB.setResultString(Utils.CB_OK);
				}
			} catch (Exception e) {
				mCB.setResultString(e.toString());
			}
		}
		DataOutputStream dos = new DataOutputStream(res.getOutputStream());
		mCB.setCommand(lenh);
		String kq = Function.alldata2server(mCB, null);
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

	public String Insert_TRU(Obj_TRU mLS) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String kq = "LOI";
		String insertTableSQL = SQL_Obj.get_sql_insert_TRU();
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			dbConnection.setAutoCommit(false);
			SQL_Obj.set_preparedStatement_TRU(preparedStatement, mLS);
			preparedStatement.addBatch();
			preparedStatement.executeBatch();
			dbConnection.commit();
			kq = Utils.CB_OK;
		} catch (SQLException e) {
			kq = e.toString();
			dbConnection.rollback();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return kq;

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
