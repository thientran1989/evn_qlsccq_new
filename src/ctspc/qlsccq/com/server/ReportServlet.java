package ctspc.qlsccq.com.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.tsuyu.jasper.db.Db;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.DB_CONFIG;
import ctspc.qlsccq.com.shared.Obj_SU_CO;
import ctspc.qlsccq.com.shared.Utils;

@SuppressWarnings("serial")
public class ReportServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {

	}
	String json = "first error";
	int mNGAY;
	int mTHANG;
	int mNAM;
	String ngay="07/07/2015";
	String title="";
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Db mDB = new Db();
//		String mDVI = request.getParameter("p1");
//		String mTT = request.getParameter("p2");
		get_date();

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("TEN_DVI", "CÔNG TY ĐIỆN LỰC TP CẦN THƠ");
		parameters.put("NGAY",ngay);
		List<Obj_SU_CO> list = new ArrayList<Obj_SU_CO>();
		try {
			list =(List<Obj_SU_CO>) get_SUCO_USE().getResultObj();
			json = Utils.CB_OK;
		} catch (Exception e) {

		}
		JasperPrint jp1 = mDB.getReportPDF4("BCSCCQ.jasper", list,
				parameters);
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jp1, output);
			byte[] bytes = output.toByteArray();
			ServletOutputStream stream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength((int) bytes.length);
			stream.write(bytes);
			stream.flush();
			stream.close();
		} catch (Exception ex) {
			try {
				response.getWriter().write("LOI: " + ex.getMessage()+"\nsize"+list.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	CallbackResult get_SUCO_USE() throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_SU_CO> list_DVI = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
				rs = st.executeQuery("Select * from "
						+ Obj_SU_CO.tag_TABLE + " order by "
						+ Obj_SU_CO.tag_TG_PHATHIEN + " desc");
				if (rs != null) {
					list_DVI = new ArrayList<Obj_SU_CO>();
					while (rs.next()) {
						Obj_SU_CO mBA = ResultSet_Obj.set_result_SUCO(rs);
						list_DVI.add(mBA);
					}
					oCB.setResultObj(list_DVI);
				}

			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return oCB;
	}
	public void get_date(){
		java.sql.Timestamp ts = getCurrentTimeStamp();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(ts.getTime()));
		ngay = "";
		try {
			mNGAY = cal.get(Calendar.DAY_OF_MONTH);
			mTHANG = cal.get(Calendar.MONTH)+1;
			mNAM = cal.get(Calendar.YEAR);
			ngay = "Cần Thơ, ngày "+mNGAY+" tháng "+mTHANG+" năm "+mNAM;
		} catch (Exception e) {

		}
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
	public static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

}