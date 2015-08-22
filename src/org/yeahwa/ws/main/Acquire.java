package org.yeahwa.ws.main;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface Acquire {
	
	@WebMethod
	@WebResult(name="result")public String invoke(@WebParam(name="msgXML")String StrXML);

}
