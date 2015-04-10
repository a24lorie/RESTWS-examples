package com.websocket.config;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.Endpoint;
import javax.websocket.server.ServerApplicationConfig;
import javax.websocket.server.ServerEndpointConfig;

public class ServerApplicationConfigImpl implements ServerApplicationConfig{
	
	private static final String ENDPOINT_PACKAGE = "com.websocket";

	@Override
	public Set<ServerEndpointConfig> getEndpointConfigs(Set<Class<? extends Endpoint>> endpointClasses) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Class<?>> getAnnotatedEndpointClasses(Set<Class<?>> scanned) {
		Set<Class<?>> endPointSet = new HashSet<Class<?>>();
		if(scanned != null){
			for(Class<?> clazz : scanned){
				if(clazz.getPackage().getName().startsWith(ENDPOINT_PACKAGE)){
					endPointSet.add(clazz);
				}
			}
		}
		return endPointSet;
	}

}
