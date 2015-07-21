package ctspc.qlsccq.com.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.Obj_TRAM;
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Obj_Text;
import ctspc.qlsccq.com.shared.Utils;

public class Tao_TUYEN extends PopupPanel{

	private static Tao_TUYENUiBinder uiBinder = GWT
			.create(Tao_TUYENUiBinder.class);
	private final GreetingServiceAsync mIodata = GWT
			.create(GreetingService.class);
	@UiField ListBox cbx_TRAMDAU;
	@UiField ListBox cbx_TRAMCUOI;
	@UiField ListBox cbx_LOAICAP;
	@UiField TextBox edt_MATUYEN;
	@UiField TextBox edt_TENTUYEN;
	@UiField TextBox edt_CHIEUDAI;
	@UiField
	SimplePanel pane_trolai;
	@UiField
	SimplePanel pane_hoantat;
	List<Obj_TRAM> local_list_tram =null;
	List<Obj_Text> list_cap=null;

	interface Tao_TUYENUiBinder extends UiBinder<Widget, Tao_TUYEN> {
	}

	public Tao_TUYEN(List<Obj_TRAM> list_tram) {
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		local_list_tram = new ArrayList<Obj_TRAM>(list_tram);
		set_combo_tram(local_list_tram);
		list_cap = Utils.get_list_CAPQUANG();
		set_combo_mangxong(list_cap);
		pane_trolai.sinkEvents(Event.ONCLICK);
		pane_trolai.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Tao_TUYEN.this.hide();
			}
		}, ClickEvent.getType());

		pane_hoantat.sinkEvents(Event.ONCLICK);
		pane_hoantat.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
//				if (lenh == Utils.TAO) {
//					show_confirm("Bạn có muốn tạo máy biến áp không ?",
//							lenh, mL_DVI,null);
//				} else {
//					if (lenh == Utils.SUA) {
//						show_confirm(
//								"Bạn có muốn cập nhật thông tin máy biến áp không ?",
//								lenh, mL_DVI,mMBA);
//					}
//				}
				final Obj_TUYEN oTRU = get_TUYEN();
				if(oTRU.getMA_TUYEN().length()>0
					& oTRU.getTEN_TUYEN().length()>0
					& oTRU.getTRAM_CUOI().length()>0
					& oTRU.getTRAM_DAU().length()>0
					& oTRU.getLOAI_CAP().length()>0){
				// kiem tra
				if(!oTRU.getTRAM_DAU().equals(oTRU.getTRAM_CUOI())){
				// start
				SC.confirm("Bạn có muốn tạo tuyến "+oTRU.getMA_TUYEN()+" không ?", new BooleanCallback() {
					public void execute(Boolean value) {
						if (value != null && value) {
							mIodata.check_tontai_TUYEN(oTRU, new AsyncCallback<CallbackResult>() {
								public void onFailure(Throwable caught) {
									SC.say("Lỗi check tuyến : \n"+caught.toString());
								}
								public void onSuccess(CallbackResult result) {
									if(result.getResultString().equals(Utils.CB_OK)){
										mIodata.tao_TUYEN(oTRU, new AsyncCallback<CallbackResult>() {
											public void onFailure(Throwable caught) {
												SC.say("Lỗi tạo tuyến : \n"+caught.toString());
											}
											public void onSuccess(CallbackResult result) {
												if(result.getResultString().equals(Utils.CB_OK)){
													SC.say("Tạo tuyến thành công !");
													Tao_TUYEN.this.hide();
												}else{
													SC.say(result.getResultString());
												}
											}
										});
									}else if(result.getResultString().equals(Utils.CB_TONTAI)){
										SC.say(oTRU.getMA_TUYEN()+" đã tồn tại !");
									}else{
										SC.say("success \n "+result.getResultString());
									}
									
								}
							});
						}
					}
				});
				//end
				}else{
					SC.say("Trạm đầu và trạm cuối phải khác nhau !");
				}
				// end kiem tra
				}else{
					SC.say("Chưa đủ dữ liệu !");
				}
			}

		}, ClickEvent.getType());
	}
	public Obj_TUYEN get_TUYEN(){
		Obj_TUYEN oTRU = new Obj_TUYEN();
		try {
			oTRU.setMA_TUYEN(edt_MATUYEN.getText().toString());
			oTRU.setTEN_TUYEN(edt_TENTUYEN.getText().toString());
			oTRU.setCHIEU_DAI(edt_CHIEUDAI.getText().toString());
			oTRU.setTRAM_DAU(local_list_tram.get(cbx_TRAMDAU.getSelectedIndex()).getMA_TRAM());
			oTRU.setTRAM_CUOI(local_list_tram.get(cbx_TRAMCUOI.getSelectedIndex()).getMA_TRAM());
			oTRU.setLOAI_CAP(list_cap.get(cbx_LOAICAP.getSelectedIndex()).KEY);
		} catch (Exception e) {
			
		}
		return oTRU;
	}
	public void set_combo_tram(List<Obj_TRAM> list_dvi){
		if(list_dvi!=null){
			for (Obj_TRAM obj_donvi : list_dvi) {
				cbx_TRAMDAU.addItem(obj_donvi.getTEN_TRAM());
				cbx_TRAMCUOI.addItem(obj_donvi.getTEN_TRAM());
			}
		}
	}
	public void set_combo_mangxong(List<Obj_Text> list_mangxong){
		if(list_mangxong!=null){
			for (Obj_Text obj_donvi : list_mangxong) {
				cbx_LOAICAP.addItem(obj_donvi.NAME);
			}
		}
	}

	@UiHandler("cbx_TRAMDAU")
	void onCbx_TRAMDAUChange(ChangeEvent event) {
		String MA_DAU = (local_list_tram.get(cbx_TRAMDAU.getSelectedIndex()).getMA_TRAM());
		String MA_CUOI = (local_list_tram.get(cbx_TRAMCUOI.getSelectedIndex()).getMA_TRAM());
		String DAU = (local_list_tram.get(cbx_TRAMDAU.getSelectedIndex()).getTEN_TRAM());
		String CUOI = (local_list_tram.get(cbx_TRAMCUOI.getSelectedIndex()).getTEN_TRAM());
		
		edt_TENTUYEN.setText("Tuyến "+DAU+" - "+CUOI);
		edt_MATUYEN.setText("TUYEN-"+MA_DAU+" - "+MA_CUOI);
	}
	@UiHandler("cbx_TRAMCUOI")
	void onCbx_TRAMCUOIChange(ChangeEvent event) {
		String MA_DAU = (local_list_tram.get(cbx_TRAMDAU.getSelectedIndex()).getMA_TRAM());
		String MA_CUOI = (local_list_tram.get(cbx_TRAMCUOI.getSelectedIndex()).getMA_TRAM());
		String DAU = (local_list_tram.get(cbx_TRAMDAU.getSelectedIndex()).getTEN_TRAM());
		String CUOI = (local_list_tram.get(cbx_TRAMCUOI.getSelectedIndex()).getTEN_TRAM());
		edt_TENTUYEN.setText("Tuyến "+DAU+" - "+CUOI);
		edt_MATUYEN.setText("TUYEN-"+MA_DAU+" - "+MA_CUOI);
	}
}

