package org.yeahwa.ws.main;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(endpointInterface="org.yeahwa.ws.main.Acquire", serviceName="acquireContents")
public class AcquireImpl implements Acquire {

	@Override
	@WebMethod
	@WebResult(name = "result")
	public String invoke(@WebParam(name = "msgXML") String StrXML) {
		// TODO Auto-generated method stub
		return null;
	}

}
