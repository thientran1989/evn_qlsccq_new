package ctspc.qlsccq.com.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.Obj_SU_CO;

import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class BC_SUCO extends Composite {

	private static BC_SUCOUiBinder uiBinder = GWT.create(BC_SUCOUiBinder.class);
	private final GreetingServiceAsync mIodata = GWT
			.create(GreetingService.class);
	@UiField(provided = true)
	CellTable<Obj_SU_CO> cell_SUCO = new CellTable<Obj_SU_CO>();
	@UiField
	Button btn_IN;
	@UiField
	DateBox mdate_FROM;
	@UiField
	Button btn_LOC;
	@UiField DateBox mdate_TO;
	boolean load = false;

	interface BC_SUCOUiBinder extends UiBinder<Widget, BC_SUCO> {
	}

	public BC_SUCO() {
		initWidget(uiBinder.createAndBindUi(this));

		// date picker
		DatePicker datePicker = new DatePicker();
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				mdate_FROM.setValue(date);
				mdate_TO.setValue(date);
			}
		});
		datePicker.setValue(new Date(), true);
		DateTimeFormat dateFormat = DateTimeFormat.getFormat("dd/MM/yyyy");
		mdate_FROM.setFormat(new DateBox.DefaultFormat(dateFormat));
		mdate_TO.setFormat(new DateBox.DefaultFormat(dateFormat));

		load = false;
		mIodata.get_SUCO_USE(new AsyncCallback<CallbackResult>() {
			public void onFailure(Throwable caught) {
				Window.alert("LỖI LẤY SỰ CỐ " + caught.toString());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(CallbackResult result) {
				List<Obj_SU_CO> list_suco = (List<Obj_SU_CO>) result
						.getResultObj();
				if (list_suco != null) {
					set_list(list_suco);
				} else {
					Window.alert("SỰ CỐ NULL\n");
				}
			}
		});
	}

	public void set_list(final List<Obj_SU_CO> my_list) {

		// noi dung
		TextColumn<Obj_SU_CO> noidungColumn = new TextColumn<Obj_SU_CO>() {
			@Override
			public String getValue(Obj_SU_CO object) {
				return object.getNOI_DUNG();
			}
		};
		// nguyen nhan
		TextColumn<Obj_SU_CO> nguyenhanColumn = new TextColumn<Obj_SU_CO>() {
			@Override
			public String getValue(Obj_SU_CO object) {
				return object.getNGUYEN_NHAN();
			}
		};
		// khac phu
		TextColumn<Obj_SU_CO> khacphucColumn = new TextColumn<Obj_SU_CO>() {
			@Override
			public String getValue(Obj_SU_CO object) {
				return object.getKHAC_PHUC();
			}
		};
		// nguyen nhan
		TextColumn<Obj_SU_CO> phamviColumn = new TextColumn<Obj_SU_CO>() {
			@Override
			public String getValue(Obj_SU_CO object) {
				return object.getPHAM_VI();
			}
		};
		// Add a ButtonCell sua
		Column<Obj_SU_CO, String> editSLBtn = new Column<Obj_SU_CO, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_SU_CO c) {
				return "Sửa";
			}
		};
		editSLBtn.setFieldUpdater(new FieldUpdater<Obj_SU_CO, String>() {
			public void update(int index, final Obj_SU_CO object, String value) {
				// sua
				SC.say("Đang phát triển");
			}
		});

		// Add a ButtonCell xoa
		Column<Obj_SU_CO, String> btn_XOA = new Column<Obj_SU_CO, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_SU_CO c) {
				return "Xoá";
			}
		};
		btn_XOA.setFieldUpdater(new FieldUpdater<Obj_SU_CO, String>() {
			public void update(final int index, final Obj_SU_CO object,
					String value) {
				SC.confirm("Bạn có chắc muốn xoá điểm này không ?",
						new BooleanCallback() {
							public void execute(Boolean value) {
								if (value != null && value) {
									// xoa
									SC.say("Đang phát triển");
								}
							}
						});

			}
		});
		cell_SUCO.setColumnWidth(btn_XOA, 20, Unit.PCT);

		// phat hien
		TextColumn<Obj_SU_CO> phathienColumn = new TextColumn<Obj_SU_CO>() {
			@Override
			public String getValue(Obj_SU_CO object) {
				try {
					return object.getTIME_PHATHIEN();
				} catch (Exception e) {
					return "PH loi";
				}

			}
		};
		// xu ly
		TextColumn<Obj_SU_CO> xulyColumn = new TextColumn<Obj_SU_CO>() {
			@Override
			public String getValue(Obj_SU_CO object) {
				try {
					return object.getTIME_XULY();
				} catch (Exception e) {
					return "XL loi";
				}

			}
		};

		if (load == false) {
			try {
				cell_SUCO.setColumnWidth(phathienColumn, 20, Unit.PCT);
				cell_SUCO.addColumn(phathienColumn, "Thời gian phát hiện");
			} catch (Exception e) {

			}
			try {
				cell_SUCO.setColumnWidth(xulyColumn, 20, Unit.PCT);
				cell_SUCO.addColumn(xulyColumn, "Thời gian xử lý");
			} catch (Exception e) {

			}
			cell_SUCO.setColumnWidth(noidungColumn, 80, Unit.PCT);
			cell_SUCO.addColumn(noidungColumn, "Nội dung sự cố");

			cell_SUCO.setColumnWidth(nguyenhanColumn, 80, Unit.PCT);
			cell_SUCO.addColumn(nguyenhanColumn, "Nguyên nhân");

			cell_SUCO.setColumnWidth(khacphucColumn, 80, Unit.PCT);
			cell_SUCO.addColumn(khacphucColumn, "Cách khắc phục");

			cell_SUCO.setColumnWidth(phamviColumn, 80, Unit.PCT);
			cell_SUCO.addColumn(phamviColumn, "Phạm vi ảnh hưởng");

			cell_SUCO.addColumn(editSLBtn, "");
			cell_SUCO.addColumn(btn_XOA, "");
		}

		AsyncDataProvider<Obj_SU_CO> provider = new AsyncDataProvider<Obj_SU_CO>() {
			@Override
			protected void onRangeChanged(HasData<Obj_SU_CO> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= my_list.size() ? my_list.size() : end;
				List<Obj_SU_CO> sub = my_list.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_SUCO);
		provider.updateRowCount(my_list.size(), true);
		cell_SUCO.setPageSize(my_list.size());
		load = true;
	}

	@UiHandler("btn_IN")
	void onBtn_INClick(ClickEvent event) {
		String url = GWT.getHostPageBaseURL() + "bcsc";
		final popupContent pp = new popupContent(url);
		pp.f.setPixelSize(Window.getClientWidth(),
				Window.getClientHeight() - 60);
		pp.setPopupPositionAndShow(new PositionCallback() {
			public void setPosition(int offsetWidth, int offsetHeight) {
				int left = (Window.getClientWidth() - offsetWidth) / 2;
				int top = (Window.getClientHeight() - offsetHeight) / 2;
				pp.setPopupPosition(left, top);
			}
		});
	}

	@UiHandler("btn_LOC")
	void onBtn_LOCClick(ClickEvent event) {
		String from = DateTimeFormat.getFormat("dd/MM/yyyy").format(mdate_FROM.getValue());
		String to = DateTimeFormat.getFormat("dd/MM/yyyy").format(mdate_TO.getValue());
		mIodata.get_SUCO_LOC(from, to,new AsyncCallback<CallbackResult>() {
			public void onFailure(Throwable caught) {
				Window.alert("LỖI LẤY SỰ CỐ " + caught.toString());
			}
			@SuppressWarnings("unchecked")
			public void onSuccess(CallbackResult result) {
				List<Obj_SU_CO> list_suco = (List<Obj_SU_CO>) result
						.getResultObj();
				if (list_suco != null) {
					set_list(list_suco);
				} else {
					Window.alert("SỰ CỐ NULL\n");
				}
			}
		});
	}
}
