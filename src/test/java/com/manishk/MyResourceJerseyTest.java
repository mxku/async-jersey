package com.manishk;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;

import static org.junit.Assert.assertEquals;

/**
 * Created by manish on 14/04/15.
 */
public class MyResourceJerseyTest extends JerseyTest{

    @Override
    protected Application configure() {
        return new ResourceConfig().packages("com.manishk");
    }

    @Test
    public void testGetIt() {
        String responseMsg = target("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
