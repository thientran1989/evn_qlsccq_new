package ctspc.qlsccq.com.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.Obj_SU_CO;
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Obj_Text;
import ctspc.qlsccq.com.shared.Obj_User;
import ctspc.qlsccq.com.shared.Utils;

import com.google.gwt.user.client.ui.ListBox;
import com.watopi.chosen.client.gwt.ChosenListBox;

public class Tao_Suco extends PopupPanel {

	private static Tao_SucoUiBinder uiBinder = GWT
			.create(Tao_SucoUiBinder.class);
	private final GreetingServiceAsync mIodata = GWT
			.create(GreetingService.class);

	@UiField
	SimplePanel pane_trolai;
	@UiField
	SimplePanel pane_hoantat;
	@UiField
	TextBox edt_TIME_PH;
	@UiField
	TextBox edt_TIME_XL;
	@UiField
	DateBox dbx_DATE_PH;
	@UiField
	DateBox dbx_DATE_XL;
	@UiField
	TextBox edt_NOIDUNG;
	@UiField
	TextBox edt_NGUYENNHAN;
	@UiField
	TextBox edt_KHACPHUC;
	@UiField TextBox edt_NGUYENNHAN_TTE;
	@UiField ListBox cbx_PHAMVI;
	@UiField ListBox cbx_DVIQLTS;
	@UiField ChosenListBox chzn_DIEM;
	Obj_SU_CO oSC = null;
	List<Obj_Text> list_DVQLTS=null;
	List<Obj_TUYEN> MLlist_tuyen = null;
	List<Obj_TRU> list_tru =null;
	Obj_User oL_USER=null;

	interface Tao_SucoUiBinder extends UiBinder<Widget, Tao_Suco> {
	}

	public Tao_Suco(Obj_User oUSER,List<Obj_TUYEN> list_tuyen) {
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		oL_USER = oUSER;
		list_DVQLTS = new ArrayList<Obj_Text>(Utils.get_list_DVQL());
		set_combo_nhanhre(list_DVQLTS);
		MLlist_tuyen = new ArrayList<Obj_TUYEN>(list_tuyen);
		set_combo_phamvi(MLlist_tuyen);
		oSC = new Obj_SU_CO();
		
		mIodata.getTRU_USE(new AsyncCallback<CallbackResult>() {
			public void onFailure(Throwable caught) {
				Window.alert("LỖI LẤY TRỤ " + caught.toString());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(CallbackResult result) {
				list_tru = (List<Obj_TRU>) result.getResultObj();
				if (list_tru != null) {
					set_combo_tru(list_tru);
				} else {
					Window.alert("TRỤ NULL\n");
				}
			}
		});

		// date phat hien
		DatePicker datePicker = new DatePicker();
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				dbx_DATE_PH.setValue(date);
				dbx_DATE_XL.setValue(date);
			}
		});
		datePicker.setValue(new Date(), true);
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
		dbx_DATE_PH.setFormat(new DateBox.DefaultFormat(dateFormat));
		dbx_DATE_XL.setFormat(new DateBox.DefaultFormat(dateFormat));

		pane_trolai.sinkEvents(Event.ONCLICK);
		pane_trolai.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Tao_Suco.this.hide();
			}

		}, ClickEvent.getType());
		pane_hoantat.sinkEvents(Event.ONCLICK);
		pane_hoantat.addHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				get_suco();
				if (edt_KHACPHUC.getText().length() > 0
						& edt_NGUYENNHAN.getText().length() > 0
						& edt_NOIDUNG.getText().length() > 0) {
					// start
					SC.confirm("Bạn có muốn tạo sự cố này không ?",
							new BooleanCallback() {
								public void execute(Boolean value) {
									if (value != null && value) {
										mIodata.tao_SUCO(oSC,
												new AsyncCallback<CallbackResult>() {
													public void onFailure(Throwable caught) {
														SC.say("Lỗi tạo sự cố : \n"
																+ caught.toString());
													}
													public void onSuccess(CallbackResult result) {
														if (result.getResultString().equals(Utils.CB_OK)) {
															SC.say("Tạo sự cố thành công !");
															Tao_Suco.this.hide();
														} else {
															SC.say(result.getResultString());
														}
													}
												});
									}
								}
							});
					// end
				} else {
					SC.say("Chưa đủ dữ liệu !");
				}
			}

		}, ClickEvent.getType());
	}

	public void get_suco() {
		oSC.setKHAC_PHUC(edt_KHACPHUC.getText().toString());
		oSC.setNGUYEN_NHAN(edt_NGUYENNHAN.getText().toString());
		oSC.setNOI_DUNG(edt_NOIDUNG.getText().toString());
		oSC.setPHAM_VI(MLlist_tuyen.get(cbx_PHAMVI.getSelectedIndex()).getTEN_TUYEN());
		oSC.setDON_VI(list_DVQLTS.get(cbx_DVIQLTS.getSelectedIndex()).KEY);
		oSC.setNGUYEN_NHAN_TTE(edt_NGUYENNHAN_TTE.getText().toString());
		oSC.setUSER_TAO(oL_USER.getUsername_mba());
		oSC.setUSER_SUA(oL_USER.getUsername_mba());
		try {
			Date date = dbx_DATE_PH.getValue();
			oSC.setTG_PHATHIEN(Client_function.date2timestamp(date));
			oSC.setTIME_PHATHIEN(edt_TIME_PH.getText().toString());
		} catch (Exception e) {
			SC.say("Lỗi : " + e.toString());
		}
		try {
			Date date = dbx_DATE_XL.getValue();
			oSC.setTG_XULY(Client_function.date2timestamp(date));
			oSC.setTIME_XULY(edt_TIME_XL.getText().toString());
		} catch (Exception e) {
			SC.say("Lỗi : " + e.toString());
		}
	}
	public void set_combo_nhanhre(List<Obj_Text> list_DVQLTS){
		if(list_DVQLTS!=null){
			for (Obj_Text obj_donvi : list_DVQLTS) {
				cbx_DVIQLTS.addItem(obj_donvi.NAME);
			}
		}
	}
	public void set_combo_phamvi(List<Obj_TUYEN> list_dvi){
		if(list_dvi!=null){
			for (Obj_TUYEN obj_donvi : list_dvi) {
				cbx_PHAMVI.addItem(obj_donvi.getTEN_TUYEN());
			}
		}
	}
	public void set_combo_tru(List<Obj_TRU> list_tru){
		if(list_tru!=null){
			chzn_DIEM = new ChosenListBox(true);
			for (Obj_TRU obj_donvi : list_tru) {
				chzn_DIEM.addItem(obj_donvi.getTRU());
			}
			chzn_DIEM.setMaxSelectedOptions(5);
			chzn_DIEM.setPlaceholderText("Choose your POINT...");
		}
	}


}
