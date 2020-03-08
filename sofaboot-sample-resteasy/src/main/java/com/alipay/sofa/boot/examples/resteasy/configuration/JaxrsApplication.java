package com.alipay.sofa.boot.examples.resteasy.configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.stereotype.Component;

/**
 * JAX-RS application
 *
 * @author Fabio Carvalho (facarvalho@paypal.com or fabiocarvalho777@gmail.com)
 */
@Component
@ApplicationPath("/sample-app/")
public class JaxrsApplication extends Application {
}
