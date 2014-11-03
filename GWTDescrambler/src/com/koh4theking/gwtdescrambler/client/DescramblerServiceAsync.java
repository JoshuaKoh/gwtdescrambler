package com.koh4theking.gwtdescrambler.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DescramblerServiceAsync {

	void descramble(String scrambled, AsyncCallback<String> callback);

}
