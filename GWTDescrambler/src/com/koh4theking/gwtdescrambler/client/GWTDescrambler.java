package com.koh4theking.gwtdescrambler.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTDescrambler implements EntryPoint , ClickHandler, KeyUpHandler {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	private final DescramblerServiceAsync descramblerService = GWT.create(DescramblerService.class);

	TextBox scrambleField = new TextBox();
	Label resultField = new Label();
	Label errorText = new Label();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		Button descrambleButton = new Button("Descramble");
		scrambleField.addKeyUpHandler(this);

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("descrambleButtonContainer").add(descrambleButton);
		RootPanel.get("scrambleFieldContainer").add(scrambleField);
		RootPanel.get("result").add(resultField);
		RootPanel.get("errorLabelContainer").add(errorText);

		// Focus the cursor on the name field when the app loads
		scrambleField.setFocus(true);
		scrambleField.selectAll();
		
		descrambleButton.addClickHandler(this);
	}
	
	@Override
	public void onClick(ClickEvent event) {
		descrambleAction();
	}

	private void descrambleAction() {
		descramblerService.descramble(scrambleField.getText(), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				errorText.setText(caught.getMessage());
			}

			@Override
			public void onSuccess(String result) {
				resultField.setText(result);
//				scrambleField.selectAll();
			}
		});
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
//		if (event.getNativeKeyCode()==KeyCodes.KEY_ENTER) {
			descrambleAction();
		}
	}
	
//}

