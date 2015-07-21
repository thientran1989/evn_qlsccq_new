package ctspc.qlsccq.com.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.Obj_TRAM;
import ctspc.qlsccq.com.shared.Obj_Text;
import ctspc.qlsccq.com.shared.Utils;

public class Tao_TRAM extends PopupPanel {

	private static Tao_TRAMUiBinder uiBinder = GWT
			.create(Tao_TRAMUiBinder.class);
	private final GreetingServiceAsync mIodata = GWT
			.create(GreetingService.class);
	@UiField TextBox edt_MATRAM;
	@UiField TextBox edt_TENTRAM;
	@UiField ListBox cbx_LOAITRAM;
	@UiField
	SimplePanel pane_trolai;
	@UiField
	SimplePanel pane_hoantat;
	List<Obj_Text> list_loaitram=null;

	interface Tao_TRAMUiBinder extends UiBinder<Widget, Tao_TRAM> {
	}

	public Tao_TRAM() {
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		list_loaitram = Utils.get_list_LOAITRAM();
		set_combo_mangxong(list_loaitram);
		pane_trolai.sinkEvents(Event.ONCLICK);
		pane_trolai.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Tao_TRAM.this.hide();
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
				
				final Obj_TRAM oTRU = get_TRAM();
				if(oTRU.getMA_TRAM().length()>0
					& oTRU.getTEN_TRAM().length()>0
					& oTRU.getLOAI_TRAM().length()>0){
				// start
				SC.confirm("Bạn có muốn tạo trạm "+oTRU.getMA_TRAM()+" không ?", new BooleanCallback() {
					public void execute(Boolean value) {
						if (value != null && value) {
							mIodata.check_tontai_TRAM(oTRU, new AsyncCallback<CallbackResult>() {
								public void onFailure(Throwable caught) {
									SC.say("Lỗi check trạm : \n"+caught.toString());
								}
								public void onSuccess(CallbackResult result) {
									if(result.getResultString().equals(Utils.CB_OK)){
										mIodata.tao_TRAM(oTRU, new AsyncCallback<CallbackResult>() {
											public void onFailure(Throwable caught) {
												SC.say("Lỗi tạo trạm : \n"+caught.toString());
											}
											public void onSuccess(CallbackResult result) {
												if(result.getResultString().equals(Utils.CB_OK)){
													SC.say("Tạo trạm thành công !");
													Tao_TRAM.this.hide();
												}else{
													SC.say(result.getResultString());
												}
											}
										});
									}else if(result.getResultString().equals(Utils.CB_TONTAI)){
										SC.say(oTRU.getMA_TRAM()+" đã tồn tại !");
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
	public Obj_TRAM get_TRAM(){
		Obj_TRAM oTRU = new Obj_TRAM();
		try {
			oTRU.setMA_TRAM(edt_MATRAM.getText().toString());
			oTRU.setTEN_TRAM(edt_TENTRAM.getText().toString());
			oTRU.setLOAI_TRAM(list_loaitram.get(cbx_LOAITRAM.getSelectedIndex()).KEY);
		} catch (Exception e) {
			
		}
		return oTRU;
	}
	public void set_combo_mangxong(List<Obj_Text> list_LOAITRAM){
		if(list_LOAITRAM!=null){
			for (Obj_Text obj_donvi : list_LOAITRAM) {
				cbx_LOAITRAM.addItem(obj_donvi.NAME);
			}
		}
	}
	

}
