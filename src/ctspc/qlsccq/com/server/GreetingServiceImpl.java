package ctspc.qlsccq.com.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import ctspc.qlsccq.com.client.GreetingService;
import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.DB_CONFIG;
import ctspc.qlsccq.com.shared.DB_SQL;
import ctspc.qlsccq.com.shared.Obj_SOI;
import ctspc.qlsccq.com.shared.Obj_SU_CO;
import ctspc.qlsccq.com.shared.Obj_TRAM;
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Obj_User;
import ctspc.qlsccq.com.shared.Obj_donvi;
import ctspc.qlsccq.com.shared.Utils;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	@Override
	public List<Obj_SU_CO> get_SUCO() throws IllegalArgumentException {
		return null;
	}

	@Override
	public
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

	@Override
	public CallbackResult tao_TRU(Obj_TRU mTRU) throws IllegalArgumentException {
		String kq = "Thêm không thành công !";
		CallbackResult mCB = new CallbackResult();
		try {
			kq = Insert_TRU(mTRU);
		} catch (SQLException e) {
			kq = e.toString();
		}
		mCB.setResultString(kq);
		return mCB;
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
	public String Insert_TRAM(Obj_TRAM mTRAM) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String kq = "LOI";
		String insertTableSQL = SQL_Obj.get_sql_insert_TRAM();
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			dbConnection.setAutoCommit(false);
			SQL_Obj.set_preparedStatement_TRAM(preparedStatement, mTRAM);
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
	
	public String Insert_TUYEN(Obj_TUYEN oQD)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement_TUYEN = null;
		PreparedStatement preparedStatement_SOI = null;
		String insertTableSQL_TUYEN = SQL_Obj.get_sql_insert_TUYEN();
		String insertTableSQL_SOI = SQL_Obj.get_sql_insert_SOI();
		String mCB = "LOI";
		try {
			dbConnection = getDBConnection();
			dbConnection.setAutoCommit(false);
			// TUYEN
			if (oQD != null) {
				preparedStatement_TUYEN = dbConnection
						.prepareStatement(insertTableSQL_TUYEN);
				SQL_Obj.set_preparedStatement_TUYEN(preparedStatement_TUYEN, oQD);
				preparedStatement_TUYEN.addBatch();
				preparedStatement_TUYEN.executeBatch();
			}
			// SOI
			preparedStatement_SOI = dbConnection
					.prepareStatement(insertTableSQL_SOI);
			for (int i = 1; i < 25; i++) {
				Obj_SOI oSOI = new Obj_SOI();
				oSOI.setTUYEN(oQD.getMA_TUYEN());
				oSOI.setSOI(i);
				oSOI.setTT_SOI("KSD");
				oSOI.setGHI_CHU("");
				SQL_Obj.set_preparedStatement_SOI(preparedStatement_SOI, oSOI);
				preparedStatement_SOI.addBatch();
			}
			preparedStatement_SOI.executeBatch();
			
			dbConnection.commit();
			mCB=(Utils.CB_OK);

		} catch (SQLException e) {
			mCB=(e.toString());
			dbConnection.rollback();

		} finally {
			if (preparedStatement_TUYEN != null) {
				preparedStatement_TUYEN.close();
			}
			if (preparedStatement_SOI != null) {
				preparedStatement_SOI.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return mCB;
	}
	public String Insert_SUCO(Obj_SU_CO mSC) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String kq = "LOI";
		String insertTableSQL = SQL_Obj.get_sql_insert_SUCO();
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
			dbConnection.setAutoCommit(false);
			SQL_Obj.set_preparedStatement_SUCO(preparedStatement, mSC);
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
	public CallbackResult check_tontai_TRU(Obj_TRU mTRU)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		CallbackResult mCB = new CallbackResult();
		mCB.setResultString(Utils.CB_OK);
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from "
					+ Obj_TRU.tag_TABLE + " where "
					+ Obj_TRU.tag_TRU + " ='" + mTRU.getTRU() + "'");
			if (rs != null) {
				while (rs.next()) {
					mCB.setResultString(Utils.CB_TONTAI);
				}
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return mCB;
	}

	@Override
	public List<Obj_TRAM> getTRAM() throws IllegalArgumentException {
		return null;
	}

	public CallbackResult getTRAM_USE() throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_TRAM> list_DVI = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
				rs = st.executeQuery("Select * from "
						+ Obj_TRAM.tag_TABLE_TRAM + " order by "
						+ Obj_TRAM.tag_MA_TRAM + " asc");
				if (rs != null) {
					list_DVI = new ArrayList<Obj_TRAM>();
					while (rs.next()) {
						Obj_TRAM mBA = ResultSet_Obj.set_result_TRAM(rs);
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

	@Override
	public CallbackResult tao_TRAM(Obj_TRAM mTRAM)
			throws IllegalArgumentException {
		String kq = "Thêm không thành công !";
		CallbackResult mCB = new CallbackResult();
		try {
			kq = Insert_TRAM(mTRAM);
		} catch (SQLException e) {
			kq = e.toString();
		}
		mCB.setResultString(kq);
		return mCB;
	}

	@Override
	public CallbackResult tao_TUYEN(Obj_TUYEN mTUYEN)
			throws IllegalArgumentException {
		String kq = "Thêm không thành công !";
		CallbackResult mCB = new CallbackResult();
		try {
			kq = Insert_TUYEN(mTUYEN);
		} catch (SQLException e) {
			kq = e.toString();
		}
		mCB.setResultString(kq);
		return mCB;
	}

	@Override
	public CallbackResult check_tontai_TRAM(Obj_TRAM mTRAM)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		CallbackResult mCB = new CallbackResult();
		mCB.setResultString(Utils.CB_OK);
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from "
					+ Obj_TRAM.tag_TABLE_TRAM + " where "
					+ Obj_TRAM.tag_MA_TRAM + " ='" + mTRAM.getMA_TRAM() + "'");
			if (rs != null) {
				while (rs.next()) {
					mCB.setResultString(Utils.CB_TONTAI);
				}
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return mCB;
	}

	@Override
	public CallbackResult check_tontai_TUYEN(Obj_TUYEN mTUYEN)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		CallbackResult mCB = new CallbackResult();
		mCB.setResultString(Utils.CB_OK);
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from "
					+ Obj_TUYEN.tag_TABLE_TUYEN + " where "
					+ Obj_TUYEN.tag_MA_TUYEN + " ='" + mTUYEN.getMA_TUYEN() + "'");
			if (rs != null) {
				while (rs.next()) {
					mCB.setResultString(Utils.CB_TONTAI);
				}
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return mCB;
	}

	@Override
	public List<Obj_TUYEN> getTUYEN() throws IllegalArgumentException {
		return null;
	}

	@Override
	public CallbackResult getTUYEN_USE() throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_TUYEN> list_DVI = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
				rs = st.executeQuery("Select * from "
						+ Obj_TUYEN.tag_TABLE_TUYEN + " order by "
						+ Obj_TUYEN.tag_MA_TUYEN + " asc");
				if (rs != null) {
					list_DVI = new ArrayList<Obj_TUYEN>();
					while (rs.next()) {
						Obj_TUYEN mBA = ResultSet_Obj.set_result_TUYEN(rs);
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

	@Override
	public List<Obj_TRU> get_TRU() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CallbackResult getTRU_USE() throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_TRU> list_DVI = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
				rs = st.executeQuery("Select * from "
						+ Obj_TRU.tag_TABLE + " order by "
						+ Obj_TRU.tag_TG_TAO + " desc");
				if (rs != null) {
					list_DVI = new ArrayList<Obj_TRU>();
					while (rs.next()) {
						Obj_TRU mBA = ResultSet_Obj.set_result_TRU(rs);
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

	@Override
	public CallbackResult getTRU_TK(String KEY) throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_TRU> list_DVI = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
				rs = st.executeQuery("Select * from "
						+ Obj_TRU.tag_TABLE + " where "+Obj_TRU.tag_TRU+" like '%"+KEY+"%'"+" order by "
						+ Obj_TRU.tag_TG_TAO + " desc");
				if (rs != null) {
					list_DVI = new ArrayList<Obj_TRU>();
					while (rs.next()) {
						Obj_TRU mBA = ResultSet_Obj.set_result_TRU(rs);
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

	@Override
	public CallbackResult getTUYEN_TK(String KEY)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_TUYEN> list_DVI = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
				rs = st.executeQuery("Select * from "
						+ Obj_TUYEN.tag_TABLE_TUYEN  + " where "+Obj_TUYEN.tag_TEN_TUYEN+" like '%"+KEY+"%'"+ " order by "
						+ Obj_TUYEN.tag_MA_TUYEN + " asc");
				if (rs != null) {
					list_DVI = new ArrayList<Obj_TUYEN>();
					while (rs.next()) {
						Obj_TUYEN mBA = ResultSet_Obj.set_result_TUYEN(rs);
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

	@Override
	public CallbackResult getTRAM_TK(String KEY)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_TRAM> list_DVI = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
				rs = st.executeQuery("Select * from "
						+ Obj_TRAM.tag_TABLE_TRAM + " where "+Obj_TRAM.tag_TEN_TRAM+" like '%"+KEY+"%'"
						+ " order by "
						+ Obj_TRAM.tag_MA_TRAM + " asc");
				if (rs != null) {
					list_DVI = new ArrayList<Obj_TRAM>();
					while (rs.next()) {
						Obj_TRAM mBA = ResultSet_Obj.set_result_TRAM(rs);
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

	@Override
	public CallbackResult xoa_tru(Obj_TRU mTRU) throws IllegalArgumentException {
			Connection dbConnection = null;
			PreparedStatement preparedStatement = null;
			CallbackResult mCB = new CallbackResult();
			String deleteTableSQL = DB_SQL.get_SQL_DELETE_TRU(mTRU);
			try {
				dbConnection = getDBConnection();
				preparedStatement = dbConnection.prepareStatement(deleteTableSQL);
				dbConnection.setAutoCommit(false);
				preparedStatement.addBatch();
				preparedStatement.executeBatch();
				dbConnection.commit();
				mCB.setResultString(Utils.CB_OK);
			} catch (SQLException e) {

				mCB.setResultString(e.toString());
				try {
					dbConnection.rollback();
				} catch (Exception e2) {

				}
			} finally {
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (dbConnection != null) {
						dbConnection.close();
					}
				} catch (Exception e2) {

				}
			}
			return mCB;
		}

	@Override
	public CallbackResult tao_SUCO(Obj_SU_CO oSC)
			throws IllegalArgumentException {
		String kq = "Thêm không thành công !";
		CallbackResult mCB = new CallbackResult();
		try {
			kq = Insert_SUCO(oSC);
		} catch (SQLException e) {
			kq = e.toString();
		}
		mCB.setResultString(kq);
		return mCB;
	}

	@Override
	public List<Obj_donvi> get_DONVI() throws IllegalArgumentException {
		return null;
	}

	@Override
	public CallbackResult getDONVI_USE() throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_donvi> list_DVI = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
				rs = st.executeQuery("Select * from "
						+ Obj_donvi.TAG_table_donvi + " order by "
						+ Obj_donvi.TAG_ma_donvi + " asc");
				if (rs != null) {
					list_DVI = new ArrayList<Obj_donvi>();
					while (rs.next()) {
						Obj_donvi mBA = ResultSet_Obj.set_result_DONVI(rs);
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

	@Override
	public CallbackResult get_SUCO_LOC(String from, String to)
			throws IllegalArgumentException {
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
				rs = st.executeQuery(DB_SQL.get_SQL_LOC_SU_CO(from, to));
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
	public Obj_User login(Obj_User mUS) throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		Obj_User kq = null;
		try {
			con = getDBConnection();
			st = con.createStatement();
			rs = st.executeQuery("Select * from " + Obj_User.TAG_table_user
					+ " where " + Obj_User.TAG_username_sc + " ='"
					+ mUS.getUsername_mba() + "' and " + Obj_User.TAG_password
					+ " ='" + mUS.getPassword() + "'");
			if (rs != null) {
				while (rs.next()) {
					kq = ResultSet_Obj.set_result_USER(rs);
				}
			} else {
				kq = null;
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return kq;
	}

	@Override
	public List<Obj_SOI> getSOI() throws IllegalArgumentException {
		return null;
	}

	@Override
	public CallbackResult getSOI_USE(Obj_TUYEN oTUYEN)
			throws IllegalArgumentException {
		Connection con;
		ResultSet rs = null;
		Statement st;
		List<Obj_SOI> list_DVI = null;
		CallbackResult oCB = null;
		oCB = new CallbackResult();
		try {
			oCB.setCommand("getds");
			con = getDBConnection();
			st = con.createStatement();
				rs = st.executeQuery(DB_SQL.get_SQL_SOI_OF_TUYEN(oTUYEN));
				if (rs != null) {
					list_DVI = new ArrayList<Obj_SOI>();
					while (rs.next()) {
						Obj_SOI mBA = ResultSet_Obj.set_result_SOI(rs);
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
	

}
