package ctspc.qlsccq.com.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel.PositionCallback;
import com.google.gwt.user.client.ui.VerticalPanel;

import ctspc.qlsccq.com.shared.CallbackResult;
import ctspc.qlsccq.com.shared.Obj_TRAM;
import ctspc.qlsccq.com.shared.Obj_TUYEN;
import ctspc.qlsccq.com.shared.Obj_User;
import ctspc.qlsccq.com.shared.Obj_donvi;

public class Main extends Composite {

	private static MainUiBinder uiBinder = GWT.create(MainUiBinder.class);
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	@UiField
	MenuBar menu_bar;
	@UiField VerticalPanel ver_main;

	interface MainUiBinder extends UiBinder<Widget, Main> {
	}

	public Main(final Obj_User oUSER) {
		initWidget(uiBinder.createAndBindUi(this));
		
		// menu them
		Command menuCommand_themtram = new Command() {
			public void execute() {
				final Tao_TRAM pp = new Tao_TRAM(oUSER);
				pp.setPopupPositionAndShow(new PositionCallback() {
					public void setPosition(int offsetWidth,
							int offsetHeight) {
						int left = (Window.getClientWidth() - offsetWidth) / 2;
						int top = (Window.getClientHeight() - offsetHeight) / 2;
						pp.setPopupPosition(left, top);
					}
				});
			}
		};
		Command menuCommand_tuyen = new Command() {
			public void execute() {
				greetingService.getTRAM_USE(new AsyncCallback<CallbackResult>() {
					public void onFailure(Throwable caught) {
						Window.alert("LỖI LẤY TRẠM "+caught.toString());
					}
					@SuppressWarnings("unchecked")
					public void onSuccess(CallbackResult result) {
						List<Obj_TRAM> list_tram = (List<Obj_TRAM>) result.getResultObj();
						if (list_tram!=null){
							final Tao_TUYEN pp = new Tao_TUYEN(list_tram,oUSER);
							pp.setPopupPositionAndShow(new PositionCallback() {
								public void setPosition(int offsetWidth,
										int offsetHeight) {
									int left = (Window.getClientWidth() - offsetWidth) / 2;
									int top = (Window.getClientHeight() - offsetHeight) / 2;
									pp.setPopupPosition(left, top);
								}
							});
						}else{
							Window.alert("TRẠM NULL");
						}
					}
				});	
				
				
			}
		};
		Command menuCommand_tru = new Command() {
			public void execute() {
				
				greetingService.getTUYEN_USE(new AsyncCallback<CallbackResult>() {
					public void onFailure(Throwable caught) {
						Window.alert("LỖI LẤY TUYẾN "+caught.toString());
					}
					@SuppressWarnings("unchecked")
					public void onSuccess(CallbackResult result) {
						final List<Obj_TUYEN> list_tuyen = (List<Obj_TUYEN>) result.getResultObj();
						if (list_tuyen!=null){
							greetingService.getDONVI_USE(new AsyncCallback<CallbackResult>(){
								public void onFailure(Throwable caught) {
									Window.alert("LỖI LẤY ĐƠN VỊ "+caught.toString());
								}
								public void onSuccess(CallbackResult result) {
									List<Obj_donvi> list_donvi = (List<Obj_donvi>) result.getResultObj();
									if (list_donvi!=null){
										// tao tru
										final Tao_TRU pp = new Tao_TRU(oUSER,list_donvi,list_tuyen);
										pp.setPopupPositionAndShow(new PositionCallback() {
											public void setPosition(int offsetWidth,
													int offsetHeight) {
												int left = (Window.getClientWidth() - offsetWidth) / 2;
												int top = (Window.getClientHeight() - offsetHeight) / 2;
												pp.setPopupPosition(left, top);
											}
										});
										// het tao tru
									}else{
										Window.alert("DV NULL\n");
									}
								
							}
							});
							
						}else{
							Window.alert("TUYẾN NULL\n");
						}
					}
				});	
				
				
			}
		};
		
		// su co
		Command menuCommand_suco = new Command() {
			public void execute() {
				greetingService.getTUYEN_USE(new AsyncCallback<CallbackResult>() {
					public void onFailure(Throwable caught) {
						Window.alert("LỖI LẤY TUYẾN "+caught.toString());
					}
					@SuppressWarnings("unchecked")
					public void onSuccess(CallbackResult result) {
						List<Obj_TUYEN> list_tuyen = (List<Obj_TUYEN>) result.getResultObj();
						if (list_tuyen!=null){
							final Tao_Suco pp = new Tao_Suco(oUSER,list_tuyen);
							pp.setPixelSize(Window.getClientWidth()-100,
									Window.getClientHeight() - 100);
							pp.setPopupPositionAndShow(new PositionCallback() {
								public void setPosition(int offsetWidth,
										int offsetHeight) {
									int left = (Window.getClientWidth() - offsetWidth) / 2;
									int top = (Window.getClientHeight() - offsetHeight) / 2;
									pp.setPopupPosition(left, top);
								}
							});
						}else{
							Window.alert("TUYẾN NULL\n");
						}
					}
				});	
				
				
			}
		};
		
		
		// menu loc du lieu
		Command menuCommand_loc_tram = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new DS_Tram());
			}
		};
		Command menuCommand_loc_tuyen = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new DS_Tuyen());
			}
		};
		Command menuCommand_loc_tru = new Command() {
			public void execute() {
				greetingService.getDONVI_USE(new AsyncCallback<CallbackResult>(){
					public void onFailure(Throwable caught) {
						Window.alert("LỖI LẤY ĐƠN VỊ "+caught.toString());
					}
					public void onSuccess(CallbackResult result) {
						@SuppressWarnings("unchecked")
						List<Obj_donvi> list_donvi = (List<Obj_donvi>) result.getResultObj();
						if (list_donvi!=null){
							// tao tru
							ver_main.clear();
							ver_main.add(new DS_Tru(list_donvi));
							// het tao tru
						}else{
							Window.alert("DV NULL\n");
						}
					
				}
				});
				
			}
		};
		Command menuCommand_loc_soi = new Command() {
			public void execute() {
				greetingService.getTUYEN_USE(new AsyncCallback<CallbackResult>(){
					public void onFailure(Throwable caught) {
						Window.alert("LỖI LẤY TUYẾN "+caught.toString());
					}
					public void onSuccess(CallbackResult result) {
						@SuppressWarnings("unchecked")
						List<Obj_TUYEN> list_donvi = (List<Obj_TUYEN>) result.getResultObj();
						if (list_donvi!=null){
							// tao tru
							ver_main.clear();
							ver_main.add(new DS_Soi(list_donvi));
							// het tao tru
						}else{
							Window.alert("TUYEN NULL\n");
						}
					
				}
				});
				
			}
		};
		
		// menu bao cao
		Command menuCommand_bc_suco = new Command() {
			public void execute() {
				ver_main.clear();
				ver_main.add(new BC_SUCO());
			}
		};
		Command menuCommand_bc_tuyen = new Command() {
			private int curPhrase = 5;
			public void execute() {
				Window.alert("" + curPhrase);
			}
		};
		Command menuCommand_bc_tru = new Command() {
			private int curPhrase = 6;
			public void execute() {
				Window.alert("" + curPhrase);
			}
		};

		menu_bar.setAutoOpen(true);
		// menu_bar.setWidth("500px");
		menu_bar.setAnimationEnabled(true);
		
		// menu them
		MenuBar themMenu = new MenuBar(true);
		menu_bar.addItem(new MenuItem("Thêm mới", themMenu));
		themMenu.addItem("Thêm Trạm", menuCommand_themtram);
		themMenu.addItem("Thêm Tuyến", menuCommand_tuyen);
		themMenu.addItem("Thêm Trụ", menuCommand_tru);
		themMenu.addItem("Thêm Sự cố", menuCommand_suco);
		
		// menu loc
		MenuBar locMenu = new MenuBar(true);
		menu_bar.addItem(new MenuItem("Tra cứu", locMenu));
		locMenu.addItem("DS Trạm", menuCommand_loc_tram);
		locMenu.addItem("DS Tuyến", menuCommand_loc_tuyen);
		locMenu.addItem("DS Trụ", menuCommand_loc_tru);
		locMenu.addItem("DS Sợi", menuCommand_loc_soi);
		
		// menu bao cao
		MenuBar baocaoMenu = new MenuBar(true);
		menu_bar.addItem(new MenuItem("Báo cáo", baocaoMenu));
		baocaoMenu.addItem("BC Sự cố", menuCommand_bc_suco);
		baocaoMenu.addItem("BC tuyến", menuCommand_bc_tuyen);
		baocaoMenu.addItem("BC trụ", menuCommand_bc_tru);
		
	}

	public Main(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	

}
