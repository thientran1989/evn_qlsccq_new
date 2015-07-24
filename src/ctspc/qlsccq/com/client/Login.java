package ctspc.qlsccq.com.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.smartgwt.client.util.SC;

import ctspc.qlsccq.com.shared.Obj_User;

public class Login extends Composite {

	private static final int COOKIE_TIMEOUT = 1000 * 60 * 60 * 24;
	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);
	private final GreetingServiceAsync mIodata = GWT
			.create(GreetingService.class);

	interface LoginUiBinder extends UiBinder<Widget, Login> {
	}

	public Login() {
		initWidget(uiBinder.createAndBindUi(this));
		tv_version.setText("1.0");
		String sessionID = Cookies.getCookie("sid");
		if (sessionID != null) {
			edt_username.setText(sessionID);
		}
	}

	@UiField
	TextBox edt_username;
	@UiField
	PasswordTextBox edt_password;
	@UiField
	Button btn_login;
	@UiField
	Label tv_version;

	public Login(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@UiHandler("btn_login")
	void onBtn_loginClick(ClickEvent event) {
		String user = "";
		String password = "";
		try {
			user = edt_username.getText().toString();
		} catch (Exception e) {

		}
		try {
			password = edt_password.getText().toString();
		} catch (Exception e) {

		}
		if (user.length() == 0 || password.length() == 0) {
			SC.say("Chưa có username hay password");
		} else {
			Obj_User mUS = new Obj_User();
			mUS.setUsername_mba(user);
			mUS.setPassword(password);

			Date expires = new Date((new Date()).getTime() + COOKIE_TIMEOUT);
			// Set the cookie value
			Cookies.setCookie(user, password, expires);
			Cookies.setCookie("sid", user, expires, null, "/", false);

			mIodata.login(mUS, new AsyncCallback<Obj_User>() {
				public void onFailure(Throwable caught) {
					Window.alert("loi " + caught.toString());
				}

				public void onSuccess(final Obj_User oUSER) {
					if (oUSER != null) {
						RootPanel.get().clear();
						RootPanel.get().add(new Main(oUSER));
					} else {
						SC.say("sai username hoặc mật khẩu !");
					}

				}
			});
		}

	}

	@UiHandler("edt_password")
	void onEdt_passwordKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_login.click();
		}
	}

	@UiHandler("edt_username")
	void onEdt_usernameKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			btn_login.click();
		}
	}
}
