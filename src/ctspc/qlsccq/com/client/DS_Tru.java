package ctspc.qlsccq.com.client;

import java.util.ArrayList;
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
import ctspc.qlsccq.com.shared.Obj_TRU;
import ctspc.qlsccq.com.shared.Obj_donvi;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.event.dom.client.ChangeEvent;

public class DS_Tru extends Composite {

	private static DS_TruUiBinder uiBinder = GWT.create(DS_TruUiBinder.class);
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	@UiField
	TextBox edt_tru;
	@UiField
	Button btn_timkiem;
	@UiField(provided = true)
	CellTable<Obj_TRU> cell_TRU = new CellTable<Obj_TRU>();
	@UiField ListBox cbx_DONVI;
	boolean load = false;
	List<Obj_donvi> list_donvi=null;
	List<Obj_TRU> list_tru = null;

	interface DS_TruUiBinder extends UiBinder<Widget, DS_Tru> {
	}

	public DS_Tru(List<Obj_donvi> list_DONVI) {
		initWidget(uiBinder.createAndBindUi(this));
		load = false;
		list_donvi = new ArrayList<Obj_donvi>(list_DONVI);
		set_combo_donvi(list_donvi);
		// lay danh sach tru
		greetingService.getTRU_USE(new AsyncCallback<CallbackResult>() {
			public void onFailure(Throwable caught) {
				Window.alert("LỖI LẤY TRỤ " + caught.toString());
			}

			@SuppressWarnings("unchecked")
			public void onSuccess(CallbackResult result) {
				list_tru = (List<Obj_TRU>) result.getResultObj();
				if (list_tru != null) {
					set_list(list_tru);
				} else {
					Window.alert("TRỤ NULL\n");
				}
			}
		});
	}

	@UiHandler("btn_timkiem")
	void onBtn_timkiemClick(ClickEvent event) {
		greetingService.getTRU_TK(edt_tru.getText().toString(),
				new AsyncCallback<CallbackResult>() {
					public void onFailure(Throwable caught) {
						Window.alert("LỖI LẤY TRỤ " + caught.toString());
					}

					@SuppressWarnings("unchecked")
					public void onSuccess(CallbackResult result) {
						List<Obj_TRU> list_tru = (List<Obj_TRU>) result
								.getResultObj();
						if (list_tru != null) {
							set_list(list_tru);
						} else {
							Window.alert("TRỤ NULL\n");
						}
					}
				});
	}

	public void set_list(final List<Obj_TRU> my_list) {
		// tru
		TextColumn<Obj_TRU> truColumn = new TextColumn<Obj_TRU>() {
			@Override
			public String getValue(Obj_TRU object) {
				return object.getTRU();
			}
		};
		// tuyen
		TextColumn<Obj_TRU> donviColumn = new TextColumn<Obj_TRU>() {
			@Override
			public String getValue(Obj_TRU object) {
				return object.getDONVI_label(list_donvi);
			}
		};
		// mang xong
		TextColumn<Obj_TRU> mangxongColumn = new TextColumn<Obj_TRU>() {
			@Override
			public String getValue(Obj_TRU object) {
				return object.getMANGXONG_label();
			}
		};
		// nhanh re
		TextColumn<Obj_TRU> nhanhreColumn = new TextColumn<Obj_TRU>() {
			@Override
			public String getValue(Obj_TRU object) {
				return object.getNHANH_RE_label();
			}
		};
		// toa do
		TextColumn<Obj_TRU> toadoColumn = new TextColumn<Obj_TRU>() {
			@Override
			public String getValue(Obj_TRU object) {
				return object.getX()+","+object.getY();
			}
		};
		// Add a ButtonCell sua
		Column<Obj_TRU, String> editSLBtn = new Column<Obj_TRU, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_TRU c) {
				return "Sửa";
			}
		};
		editSLBtn.setFieldUpdater(new FieldUpdater<Obj_TRU, String>() {
			public void update(int index, final Obj_TRU object, String value) {
				// sua
				SC.say("Đang phát triển");
			}
		});

		// Add a ButtonCell xoa
		Column<Obj_TRU, String> btn_XOA = new Column<Obj_TRU, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_TRU c) {
				return "Xoá";
			}
		};
		btn_XOA.setFieldUpdater(new FieldUpdater<Obj_TRU, String>() {
			public void update(final int index, final Obj_TRU object,
					String value) {
				SC.confirm("Bạn có chắc muốn xoá điểm này không ?",
						new BooleanCallback() {
							public void execute(Boolean value) {
								if (value != null && value) {
									greetingService.xoa_tru(object, new AsyncCallback<CallbackResult>() {
										public void onFailure(Throwable caught) {
											SC.say("Lỗi xoá điểm : "+caught.toString());
										}
										public void onSuccess(CallbackResult result) {
											if(result.getResultString().equals("OK")){
												my_list.remove(index);
												set_list(my_list);
												SC.say("Đã xoá thành công "+object.getTRU()+" !");
											}else{
												SC.say(result.getResultString());
											}
											
										}
									});
								}
							}
						});

			}
		});
		cell_TRU.setColumnWidth(btn_XOA, 20, Unit.PCT);

		if (load == false) {
			cell_TRU.setColumnWidth(truColumn, 40, Unit.PCT);
			cell_TRU.addColumn(truColumn, "TRỤ");
			cell_TRU.setColumnWidth(donviColumn, 60, Unit.PCT);
			cell_TRU.addColumn(donviColumn, "ĐƠN VỊ");
			cell_TRU.setColumnWidth(mangxongColumn, 40, Unit.PCT);
			cell_TRU.addColumn(mangxongColumn, "MĂNGXÔNG");
			cell_TRU.setColumnWidth(nhanhreColumn, 40, Unit.PCT);
			cell_TRU.addColumn(nhanhreColumn, "NHÁNH RẼ");
			cell_TRU.addColumn(toadoColumn, "TOẠ ĐỘ");
			cell_TRU.addColumn(editSLBtn, "");
			cell_TRU.addColumn(btn_XOA, "");
		}

		AsyncDataProvider<Obj_TRU> provider = new AsyncDataProvider<Obj_TRU>() {
			@Override
			protected void onRangeChanged(HasData<Obj_TRU> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= my_list.size() ? my_list.size() : end;
				List<Obj_TRU> sub = my_list.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_TRU);
		provider.updateRowCount(my_list.size(), true);
		cell_TRU.setPageSize(my_list.size());
		load = true;
	}
	@UiHandler("edt_tru")
	void onEdt_truKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_timkiem.click();
		}
	}
	public void set_combo_donvi(List<Obj_donvi> list_dvi){
		if(list_dvi!=null){
			for (Obj_donvi obj_donvi : list_dvi) {
				cbx_DONVI.addItem(obj_donvi.getTen_donvi());
			}
		}
	}
	@UiHandler("cbx_DONVI")
	void onCbx_DONVIChange(ChangeEvent event) {
		String MADV = list_donvi.get(cbx_DONVI.getSelectedIndex()).getMa_donvi();
		List<Obj_TRU> new_list = new ArrayList<Obj_TRU>();
		if(list_tru!=null){
			for (Obj_TRU obj_TRU : list_tru) {
				if(obj_TRU.getMA_DVI().equals(MADV)){
					new_list.add(obj_TRU);
				}
			}
			if(new_list!=null){
				set_list(new_list);
			}
		}
		
	}
}
