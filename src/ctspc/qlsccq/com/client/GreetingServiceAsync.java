package ctspc.qlsccq.com.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.Obj_SU_CO;
import ctspc.qlsccq.com.shared.Obj_TRAM;
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Obj_donvi;

public interface GreetingServiceAsync {

	void get_SUCO(AsyncCallback<List<Obj_SU_CO>> callback);

	void get_SUCO_USE(AsyncCallback<CallbackResult> callback);

	void tao_TRU(Obj_TRU mTRU, AsyncCallback<CallbackResult> callback);

	void check_tontai_TRU(Obj_TRU mTRU, AsyncCallback<CallbackResult> callback);

	void getTRAM(AsyncCallback<List<Obj_TRAM>> callback);

	void getTRAM_USE(AsyncCallback<CallbackResult> callback);

	void tao_TRAM(Obj_TRAM mTRAM, AsyncCallback<CallbackResult> callback);

	void tao_TUYEN(Obj_TUYEN mTUYEN, AsyncCallback<CallbackResult> callback);

	void check_tontai_TRAM(Obj_TRAM mTRAM,
			AsyncCallback<CallbackResult> callback);

	void check_tontai_TUYEN(Obj_TUYEN mTUYEN,
			AsyncCallback<CallbackResult> callback);

	void getTUYEN(AsyncCallback<List<Obj_TUYEN>> callback);

	void getTUYEN_USE(AsyncCallback<CallbackResult> callback);

	void get_TRU(AsyncCallback<List<Obj_TRU>> callback);

	void getTRU_USE(AsyncCallback<CallbackResult> callback);

	void getTRU_TK(String KEY, AsyncCallback<CallbackResult> callback);

	void getTUYEN_TK(String KEY, AsyncCallback<CallbackResult> callback);

	void getTRAM_TK(String KEY, AsyncCallback<CallbackResult> callback);

	void xoa_tru(Obj_TRU mTRU, AsyncCallback<CallbackResult> callback);

	void tao_SUCO(Obj_SU_CO oSC, AsyncCallback<CallbackResult> callback);

	void get_DONVI(AsyncCallback<List<Obj_donvi>> callback);

	void getDONVI_USE(AsyncCallback<CallbackResult> callback);

	void get_SUCO_LOC(String from, String to,
			AsyncCallback<CallbackResult> callback);

//	void greetServer(String input, AsyncCallback<String> callback)
//			throws IllegalArgumentException;
}
