package com.koh4theking.gwtdescrambler.server;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.koh4theking.gwtdescrambler.client.DescramblerService;

public class DescramblerServiceImpl extends RemoteServiceServlet implements DescramblerService {

	@Override
	public String descramble(String scrambled) {
		ServletContext servletContext = getServletContext();
		InputStream is = servletContext.getResourceAsStream("/WEB-INF/10kwords.txt");
		if (is == null)
			throw new RuntimeException("uh-oh, stream was null");
		InputStreamReader isr = new InputStreamReader(is);
		WordIndex index = new WordIndex(isr);
		String result = index.lookup(scrambled);
		return result;
	}
	
}
