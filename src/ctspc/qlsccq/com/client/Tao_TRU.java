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

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Obj_Text;
import ctspc.qlsccq.com.shared.Obj_donvi;
import ctspc.qlsccq.com.shared.Utils;

public class Tao_TRU extends PopupPanel {

	private static Tao_TRUUiBinder uiBinder = GWT.create(Tao_TRUUiBinder.class);
	private final GreetingServiceAsync mIodata = GWT
			.create(GreetingService.class);
	@UiField ListBox cbx_TUYEN;
	@UiField ListBox cbx_MANGXONG;
	@UiField TextBox edt_TRU;
	@UiField
	SimplePanel pane_trolai;
	@UiField
	SimplePanel pane_hoantat;
	@UiField ListBox cbx_NHANHRE;
	@UiField ListBox cbx_DONVI;
	List<Obj_Text> list_mangxong=null;
	List<Obj_Text> list_nhanhre=null;
	List<Obj_TUYEN> list_tuyen=null;
	List<Obj_donvi> list_donvi=null;

	interface Tao_TRUUiBinder extends UiBinder<Widget, Tao_TRU> {
	}

	public Tao_TRU(List<Obj_donvi> list_DONVI,List<Obj_TUYEN> list_TUYEN) {
		
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		list_donvi = new ArrayList<Obj_donvi>(list_DONVI);
		list_tuyen = new ArrayList<Obj_TUYEN>(list_TUYEN);
		set_combo_donvi(list_donvi);
		set_combo_tuyen(list_TUYEN);
		list_mangxong = Utils.get_list_MANGXONG();
		set_combo_mangxong(list_mangxong);
		list_nhanhre = Utils.get_NHANHRE();
		set_combo_nhanhre(list_nhanhre);
		pane_trolai.sinkEvents(Event.ONCLICK);
		pane_trolai.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Tao_TRU.this.hide();
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
				
				final Obj_TRU oTRU = get_TRU();
				if(oTRU.getTRU().length()>0
					& oTRU.getTUYEN().length()>0
					& oTRU.getMANGXONG().length()>0){
				// start
				SC.confirm("Bạn có muốn tạo trụ "+oTRU.TRU+" không ?", new BooleanCallback() {
					public void execute(Boolean value) {
						if (value != null && value) {
							mIodata.check_tontai_TRU(oTRU, new AsyncCallback<CallbackResult>() {
								public void onFailure(Throwable caught) {
									SC.say("Lỗi check trụ : \n"+caught.toString());
								}
								public void onSuccess(CallbackResult result) {
									if(result.getResultString().equals(Utils.CB_OK)){
										mIodata.tao_TRU(oTRU, new AsyncCallback<CallbackResult>() {
											public void onFailure(Throwable caught) {
												SC.say("Lỗi tạo trụ : \n"+caught.toString());
											}
											public void onSuccess(CallbackResult result) {
												if(result.getResultString().equals(Utils.CB_OK)){
													SC.say("Tạo trụ thành công !");
													Tao_TRU.this.hide();
												}else{
													SC.say(result.getResultString());
												}
											}
										});
									}else if(result.getResultString().equals(Utils.CB_TONTAI)){
										if(oTRU.NHANH_RE.equals("NHANHRE")){
											mIodata.tao_TRU(oTRU, new AsyncCallback<CallbackResult>() {
												public void onFailure(Throwable caught) {
													SC.say("Lỗi tạo trụ : \n"+caught.toString());
												}
												public void onSuccess(CallbackResult result) {
													if(result.getResultString().equals(Utils.CB_OK)){
														SC.say("Tạo trụ thành công !");
														Tao_TRU.this.hide();
													}else{
														SC.say(result.getResultString());
													}
												}
											});
										}else{
											SC.say(oTRU.getTRU()+" đã tồn tại !");
										}
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
					SC.say("Chưa đủ dữ liệu !");
				}
			}

		}, ClickEvent.getType());
	}
	public Obj_TRU get_TRU(){
		Obj_TRU oTRU = new Obj_TRU();
		try {
			oTRU.setTRU(edt_TRU.getText().toString());
			oTRU.setX("0");
			oTRU.setY("0");
			oTRU.setTUYEN(list_tuyen.get(cbx_TUYEN.getSelectedIndex()).getMA_TUYEN());
			oTRU.setMANGXONG(list_mangxong.get(cbx_MANGXONG.getSelectedIndex()).KEY);
			oTRU.setNHANH_RE(list_nhanhre.get(cbx_NHANHRE.getSelectedIndex()).KEY);
			oTRU.setMA_DVI(list_donvi.get(cbx_DONVI.getSelectedIndex()).ma_donvi);
		} catch (Exception e) {
			
		}
		return oTRU;
	}
	public void set_combo_donvi(List<Obj_donvi> list_dvi){
		if(list_dvi!=null){
			for (Obj_donvi obj_donvi : list_dvi) {
				cbx_DONVI.addItem(obj_donvi.getTen_donvi());
			}
		}
	}
	public void set_combo_tuyen(List<Obj_TUYEN> list_dvi){
		if(list_dvi!=null){
			for (Obj_TUYEN obj_donvi : list_dvi) {
				cbx_TUYEN.addItem(obj_donvi.getTEN_TUYEN());
			}
		}
	}
	public void set_combo_mangxong(List<Obj_Text> list_mangxong){
		if(list_mangxong!=null){
			for (Obj_Text obj_donvi : list_mangxong) {
				cbx_MANGXONG.addItem(obj_donvi.NAME);
			}
		}
	}
	public void set_combo_nhanhre(List<Obj_Text> list_nhanhre){
		if(list_nhanhre!=null){
			for (Obj_Text obj_donvi : list_nhanhre) {
				cbx_NHANHRE.addItem(obj_donvi.NAME);
			}
		}
	}
	

}
