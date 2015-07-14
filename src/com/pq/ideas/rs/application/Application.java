package com.pq.ideas.rs.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.EncodingFilter;

/**
 * Application class that define the packages, services and filters, that also can be made via web.xml file
 * 
 * @author kvillaca
 *
 */
@ApplicationPath("/rest")
public class Application extends ResourceConfig {
	
	@SuppressWarnings("unchecked")
	public Application() {
		packages("com.pq.ideas.rs.endpoints.v1", "com.pq.ideas.rs.filters");
		register(EntityFilteringFeature.class);
		EncodingFilter.enableFor(this, GZipEncoder.class);
	}
}
