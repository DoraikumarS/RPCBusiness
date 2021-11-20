package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import org.apache.xmlrpc.*;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;


@RestController
public class RPCServiceStarter {

	
	
	@GetMapping(path="/startServere")
	public String startServer() throws XmlRpcException {
	
		WebServer server = new WebServer(8084);
		try {
			PropertyHandlerMapping handlerMapping = new PropertyHandlerMapping();
			handlerMapping.addHandler("message", BusinessHandler.class);
			XmlRpcServer xmlRpcServer = server.getXmlRpcServer();
			xmlRpcServer.setHandlerMapping(handlerMapping);

			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
			serverConfig.setEnabledForExtensions(true);
			serverConfig.setContentLengthOptional(false);

			server.start();
	
			System.out.println("Server started for xmlrpc requests....");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return "post your requests now";
	}
}
