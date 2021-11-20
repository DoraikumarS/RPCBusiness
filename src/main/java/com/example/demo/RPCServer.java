package com.example.demo;

import java.io.IOException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RPCServer {

	
	@GetMapping(path="/startRPCServer")
	public String startServer() throws IOException, XmlRpcException {
		
		PropertyHandlerMapping propertyHandlerMapping = new PropertyHandlerMapping();
		propertyHandlerMapping.addHandler("rpc_server", BusinessHandler.class);
		
		WebServer server = new WebServer(8084);
		
		server.getXmlRpcServer().setHandlerMapping(propertyHandlerMapping);
		server.start();
		
		return "RPC Server started....";
	}
	
	
}
