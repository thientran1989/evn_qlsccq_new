package ctspc.qlsccq.com.client;

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
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Utils;

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
	@UiField
	TextBox edt_PHAMVI;
	@UiField
	TextBox edt_DONVI;
	@UiField TextBox edt_NGUYENNHAN_TTE;
	Obj_SU_CO oSC = null;

	interface Tao_SucoUiBinder extends UiBinder<Widget, Tao_Suco> {
	}

	public Tao_Suco(List<Obj_TUYEN> list_tuyen) {
		setWidget(uiBinder.createAndBindUi(this));
		super.setGlassEnabled(true);
		super.center();
		oSC = new Obj_SU_CO();

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
		oSC.setPHAM_VI(edt_PHAMVI.getText().toString());
		oSC.setDON_VI(edt_DONVI.getText().toString());
		oSC.setNGUYEN_NHAN_TTE(edt_NGUYENNHAN_TTE.getText().toString());
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

}
