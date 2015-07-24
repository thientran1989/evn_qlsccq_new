package ctspc.qlsccq.com.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.smartgwt.client.util.SC;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.Obj_SOI;
import ctspc.qlsccq.com.shared.Obj_TUYEN;

import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

public class DS_Soi extends Composite{

	private static DS_SoiUiBinder uiBinder = GWT.create(DS_SoiUiBinder.class);
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	@UiField(provided=true) CellTable<Obj_SOI> cell_SOI = new CellTable<Obj_SOI>();
	@UiField ListBox cbx_TUYEN;
	boolean load = false;
	List<Obj_TUYEN> mLocal_TUYEN=null;

	interface DS_SoiUiBinder extends UiBinder<Widget, DS_Soi> {
	}

	public DS_Soi(List<Obj_TUYEN> list_tuyen) {
		initWidget(uiBinder.createAndBindUi(this));
		mLocal_TUYEN = new ArrayList<Obj_TUYEN>(list_tuyen);
		set_combo_tuyen(mLocal_TUYEN);
	}
	
	public void set_list(final List<Obj_SOI> my_list) {
		// SOI
		TextColumn<Obj_SOI> soiColumn = new TextColumn<Obj_SOI>() {
			@Override
			public String getValue(Obj_SOI object) {
				return String.valueOf(object.getSOI());
			}
		};
		// TT SOI
		TextColumn<Obj_SOI> ttsoiColumn = new TextColumn<Obj_SOI>() {
			@Override
			public String getValue(Obj_SOI object) {
				return object.getTT_SOI();
			}
		};
		// GHI CHU
		TextColumn<Obj_SOI> ghichuColumn = new TextColumn<Obj_SOI>() {
			@Override
			public String getValue(Obj_SOI object) {
				return object.getGHI_CHU();
			}
		};
		
		// Add a ButtonCell sua
		Column<Obj_SOI, String> editSLBtn = new Column<Obj_SOI, String>(
				new ButtonCell()) {
			@Override
			public String getValue(Obj_SOI c) {
				return "Sửa";
			}
		};
		editSLBtn.setFieldUpdater(new FieldUpdater<Obj_SOI, String>() {
			public void update(int index, final Obj_SOI object, String value) {
				// sua
				SC.say("Đang phát triển");
			}
		});

		

		if (load == false) {
			cell_SOI.addColumn(soiColumn, "SỢI");
			cell_SOI.addColumn(ttsoiColumn, "TÌNH TRẠNG");
			cell_SOI.addColumn(ghichuColumn, "GHI CHÚ");
			cell_SOI.addColumn(editSLBtn, "");
		}

		AsyncDataProvider<Obj_SOI> provider = new AsyncDataProvider<Obj_SOI>() {
			@Override
			protected void onRangeChanged(HasData<Obj_SOI> display) {
				int start = display.getVisibleRange().getStart();
				int end = start + display.getVisibleRange().getLength();
				end = end >= my_list.size() ? my_list.size() : end;
				List<Obj_SOI> sub = my_list.subList(start, end);
				updateRowData(start, sub);
			}
		};
		provider.addDataDisplay(cell_SOI);
		provider.updateRowCount(my_list.size(), true);
		cell_SOI.setPageSize(my_list.size());
		load = true;
	}
	@UiHandler("cbx_TUYEN")
	void onCbx_TUYENChange(ChangeEvent event) {
		if(mLocal_TUYEN!=null){
			if(mLocal_TUYEN.size()>0){ 
				Obj_TUYEN oTUYEN = null;
				try {
					oTUYEN = mLocal_TUYEN.get(cbx_TUYEN.getSelectedIndex());
				} catch (Exception e) {
					
				}
				if(oTUYEN!=null){
					greetingService.getSOI_USE(oTUYEN,new AsyncCallback<CallbackResult>() {
						public void onFailure(Throwable caught) {
							Window.alert("LỖI LẤY SỢI " + caught.toString());
						}
						@SuppressWarnings("unchecked")
						public void onSuccess(CallbackResult result) {
							List<Obj_SOI> list_soi = (List<Obj_SOI>) result.getResultObj();
							if (list_soi != null) {
								set_list(list_soi);
							} else {
								Window.alert("SỢI NULL\n");
							}
						}
					});
				}
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
}
