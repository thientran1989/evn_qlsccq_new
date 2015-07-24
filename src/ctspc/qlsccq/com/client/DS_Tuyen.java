package ctspc.qlsccq.com.client;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.Obj_TUYEN;

public class DS_Tuyen extends Composite{

	private static DS_TuyenUiBinder uiBinder = GWT
			.create(DS_TuyenUiBinder.class);
	private final GreetingServiceAsync mIodata = GWT
			.create(GreetingService.class);
	@UiField TextBox edt_tuyen;
	boolean load =false;
	@UiField Button btn_timkiem;
	@UiField(provided=true) CellTable<Obj_TUYEN> cell_TUYEN = new CellTable<Obj_TUYEN>();

	interface DS_TuyenUiBinder extends UiBinder<Widget, DS_Tuyen> {
	}

	public DS_Tuyen() {
		initWidget(uiBinder.createAndBindUi(this));
		load =false;
		// lay danh sach tru
		mIodata.getTUYEN_USE(new AsyncCallback<CallbackResult>() {
					public void onFailure(Throwable caught) {
						Window.alert("LỖI LẤY TUYẾN "+caught.toString());
					}
					@SuppressWarnings("unchecked")
					public void onSuccess(CallbackResult result) {
						List<Obj_TUYEN> list_tru = (List<Obj_TUYEN>) result.getResultObj();
						if (list_tru!=null){
							set_list(list_tru);
						}else{
							Window.alert("TUYẾN NULL\n");
						}
					}
				});	
	}

	@UiHandler("btn_timkiem")
	void onBtn_timkiemClick(ClickEvent event) {
		mIodata.getTUYEN_TK(edt_tuyen.getText().toString(),
				new AsyncCallback<CallbackResult>() {
					public void onFailure(Throwable caught) {
						Window.alert("LỖI LẤY TRỤ " + caught.toString());
					}

					@SuppressWarnings("unchecked")
					public void onSuccess(CallbackResult result) {
						List<Obj_TUYEN> list_tru = (List<Obj_TUYEN>) result
								.getResultObj();
						if (list_tru != null) {
							set_list(list_tru);
						} else {
							Window.alert("TRỤ NULL\n");
						}
					}
				});
	}
	public void set_list(final List<Obj_TUYEN> my_list) {
		// so may
		TextColumn<Obj_TUYEN> tentuyenColumn = new TextColumn<Obj_TUYEN>() {
			@Override
			public String getValue(Obj_TUYEN object) {
				return object.getTEN_TUYEN();
			}
		};
		// CHIEU DAI
		TextColumn<Obj_TUYEN> chieudaiColumn = new TextColumn<Obj_TUYEN>() {
			@Override
			public String getValue(Obj_TUYEN object) {
				return object.getCHIEU_DAI();
			}
		};
		// LOAI CAP
		TextColumn<Obj_TUYEN> loaicapColumn = new TextColumn<Obj_TUYEN>() {
			@Override
			public String getValue(Obj_TUYEN object) {
				return object.getLOAI_CAP_label();
			}
		};
		// tt soi
		TextColumn<Obj_TUYEN> TT_SOI_Column = new TextColumn<Obj_TUYEN>() {
			@Override
			public String getValue(Obj_TUYEN object) {
				return object.getTT_SOI();
			}
		};
		// Add a ButtonCell sua
		Column<Obj_TUYEN, String> editSLBtn = new Column<Obj_TUYEN, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_TUYEN c) {
				return "Sửa";
			}
		};
		editSLBtn.setFieldUpdater(new FieldUpdater<Obj_TUYEN, String>() {
			public void update(int index, final Obj_TUYEN object, String value) {
				// sua
				SC.say("Đang phát triển");
			}
		});

		// Add a ButtonCell xoa
		Column<Obj_TUYEN, String> btn_XOA = new Column<Obj_TUYEN, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_TUYEN c) {
				return "Xoá";
			}
		};
		btn_XOA.setFieldUpdater(new FieldUpdater<Obj_TUYEN, String>() {
			public void update(final int index, final Obj_TUYEN object, String value) {
				SC.confirm("Bạn có chắc muốn xoá điểm này không ?", new BooleanCallback() {
					public void execute(Boolean value) {
						if (value != null && value) {
							// xoa
							SC.say("Đang phát triển");
						}
					}
				});
				
			}
		});
		cell_TUYEN.setColumnWidth(btn_XOA, 20, Unit.PCT);

		if (load == false) {
			cell_TUYEN.setColumnWidth(tentuyenColumn, 60, Unit.PCT);
			cell_TUYEN.addColumn(tentuyenColumn, "TUYẾN");
			cell_TUYEN.setColumnWidth(chieudaiColumn, 30, Unit.PCT);
			cell_TUYEN.addColumn(chieudaiColumn, "CHIỀU DÀI");
			cell_TUYEN.setColumnWidth(loaicapColumn, 40, Unit.PCT);
			cell_TUYEN.addColumn(loaicapColumn, "LOẠI CÁP");
			cell_TUYEN.addColumn(TT_SOI_Column, "THÔNG TIN SỢI");
			cell_TUYEN.addColumn(editSLBtn, "");
			cell_TUYEN.addColumn(btn_XOA, "");
		}

		AsyncDataProvider<Obj_TUYEN> provider = new AsyncDataProvider<Obj_TUYEN>() {
			@Override
			protected void onRangeChanged(HasData<Obj_TUYEN> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= my_list.size() ? my_list.size() : end;
				List<Obj_TUYEN> sub = my_list.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_TUYEN);
		provider.updateRowCount(my_list.size(), true);
		cell_TUYEN.setPageSize(my_list.size());
		load = true;
	}
	@UiHandler("edt_tuyen")
	void onEdt_tuyenKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_timkiem.click();
		}
	}
}
