package ctspc.qlsccq.com.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.Obj_SU_CO;
import ctspc.qlsccq.com.shared.Obj_TRAM;
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Obj_donvi;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	
	List<Obj_SU_CO> get_SUCO() throws IllegalArgumentException;
	List<Obj_donvi> get_DONVI() throws IllegalArgumentException;
	List<Obj_TRU> get_TRU() throws IllegalArgumentException;
	List<Obj_TRAM> getTRAM() throws IllegalArgumentException;
	CallbackResult get_SUCO_USE() throws IllegalArgumentException;
	
	// tru
	CallbackResult tao_TRU(Obj_TRU mTRU) throws IllegalArgumentException;
	CallbackResult check_tontai_TRU(Obj_TRU mTRU) throws IllegalArgumentException;
	CallbackResult getTRU_USE() throws IllegalArgumentException;
	CallbackResult getTRU_TK(String KEY) throws IllegalArgumentException;
	CallbackResult xoa_tru(Obj_TRU mTRU) throws IllegalArgumentException;
	
	
	// tram
	CallbackResult tao_TRAM(Obj_TRAM mTRAM) throws IllegalArgumentException;
	CallbackResult check_tontai_TRAM(Obj_TRAM mTRAM) throws IllegalArgumentException;
	CallbackResult getTRAM_USE() throws IllegalArgumentException;
	CallbackResult getTRAM_TK(String KEY) throws IllegalArgumentException;
	// tuyen
	CallbackResult tao_TUYEN(Obj_TUYEN mTUYEN) throws IllegalArgumentException;
	CallbackResult check_tontai_TUYEN(Obj_TUYEN mTUYEN) throws IllegalArgumentException;
	List<Obj_TUYEN> getTUYEN() throws IllegalArgumentException;
	CallbackResult getTUYEN_USE() throws IllegalArgumentException;
	CallbackResult getTUYEN_TK(String KEY) throws IllegalArgumentException;
	
	//su co
	CallbackResult tao_SUCO(Obj_SU_CO oSC) throws IllegalArgumentException;
	CallbackResult get_SUCO_LOC(String from,String to) throws IllegalArgumentException;
	
	// don vi
	CallbackResult getDONVI_USE() throws IllegalArgumentException;
}
