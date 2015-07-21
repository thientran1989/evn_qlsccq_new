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
import ctspc.qlsccq.com.shared.Obj_TRAM;

public class DS_Tram extends Composite{

	private static DS_TramUiBinder uiBinder = GWT.create(DS_TramUiBinder.class);
	private final GreetingServiceAsync mIodata = GWT
			.create(GreetingService.class);
	@UiField Button btn_timkiem;
	@UiField TextBox edt_tram;
	@UiField(provided=true) CellTable<Obj_TRAM> cell_TRAM = new CellTable<Obj_TRAM>();
	boolean load =false;

	interface DS_TramUiBinder extends UiBinder<Widget, DS_Tram> {
	}

	public DS_Tram() {
		initWidget(uiBinder.createAndBindUi(this));
		load =false;
		// lay danh sach tru
		mIodata.getTRAM_USE(new AsyncCallback<CallbackResult>() {
					public void onFailure(Throwable caught) {
						Window.alert("LỖI LẤY TRẠM "+caught.toString());
					}
					@SuppressWarnings("unchecked")
					public void onSuccess(CallbackResult result) {
						List<Obj_TRAM> list_tru = (List<Obj_TRAM>) result.getResultObj();
						if (list_tru!=null){
							set_list(list_tru);
						}else{
							Window.alert("TRẠM NULL\n");
						}
					}
				});	
	}

	@UiHandler("btn_timkiem")
	void onBtn_timkiemClick(ClickEvent event) {
		mIodata.getTRAM_TK(edt_tram.getText().toString(),
				new AsyncCallback<CallbackResult>() {
					public void onFailure(Throwable caught) {
						Window.alert("LỖI LẤY TRỤ " + caught.toString());
					}

					@SuppressWarnings("unchecked")
					public void onSuccess(CallbackResult result) {
						List<Obj_TRAM> list_tru = (List<Obj_TRAM>) result
								.getResultObj();
						if (list_tru != null) {
							set_list(list_tru);
						} else {
							Window.alert("TRỤ NULL\n");
						}
					}
				});
	}
	public void set_list(final List<Obj_TRAM> my_list) {
		// so may
		TextColumn<Obj_TRAM> matramColumn = new TextColumn<Obj_TRAM>() {
			@Override
			public String getValue(Obj_TRAM object) {
				return object.getMA_TRAM();
			}
		};
		// ma so tai san
		TextColumn<Obj_TRAM> tentramColumn = new TextColumn<Obj_TRAM>() {
			@Override
			public String getValue(Obj_TRAM object) {
				return object.getTEN_TRAM();
			}
		};
		// don vi quan ly
		TextColumn<Obj_TRAM> loaitramColumn = new TextColumn<Obj_TRAM>() {
			@Override
			public String getValue(Obj_TRAM object) {
				return object.getLOAI_TRAM_label();
			}
		};
		// Add a ButtonCell sua
		Column<Obj_TRAM, String> editSLBtn = new Column<Obj_TRAM, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_TRAM c) {
				return "Sửa";
			}
		};
		editSLBtn.setFieldUpdater(new FieldUpdater<Obj_TRAM, String>() {
			public void update(int index, final Obj_TRAM object, String value) {
				// sua
				SC.say("Đang phát triển");
			}
		});

		// Add a ButtonCell xoa
		Column<Obj_TRAM, String> btn_XOA = new Column<Obj_TRAM, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_TRAM c) {
				return "Xoá";
			}
		};
		btn_XOA.setFieldUpdater(new FieldUpdater<Obj_TRAM, String>() {
			public void update(final int index, final Obj_TRAM object, String value) {
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
		cell_TRAM.setColumnWidth(btn_XOA, 20, Unit.PCT);

		if (load == false) {
			cell_TRAM.setColumnWidth(matramColumn, 40, Unit.PCT);
			cell_TRAM.addColumn(matramColumn, "MÃ TRẠM");
			cell_TRAM.setColumnWidth(tentramColumn, 60, Unit.PCT);
			cell_TRAM.addColumn(tentramColumn, "TÊN TRẠM");
			cell_TRAM.setColumnWidth(loaitramColumn, 40, Unit.PCT);
			cell_TRAM.addColumn(loaitramColumn, "LOẠI TRẠM");
			cell_TRAM.addColumn(editSLBtn, "");
			cell_TRAM.addColumn(btn_XOA, "");
		}

		AsyncDataProvider<Obj_TRAM> provider = new AsyncDataProvider<Obj_TRAM>() {
			@Override
			protected void onRangeChanged(HasData<Obj_TRAM> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= my_list.size() ? my_list.size() : end;
				List<Obj_TRAM> sub = my_list.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_TRAM);
		provider.updateRowCount(my_list.size(), true);
		cell_TRAM.setPageSize(my_list.size());
		load = true;
	}
	@UiHandler("edt_tram")
	void onEdt_tramKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_timkiem.click();
		}
	}
}
