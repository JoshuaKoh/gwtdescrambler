package com.koh4theking.gwtdescrambler.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("descramble")
public interface DescramblerService extends RemoteService {
	
	String descramble(String scrambled);
	
	
	
}
