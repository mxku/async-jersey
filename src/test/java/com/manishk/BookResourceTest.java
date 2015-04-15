package com.manishk;

import junit.framework.Assert;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import java.util.Collection;

/**
 * Created by manish on 14/04/15.
 */
public class BookResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig().packages("com.manishk");
    }

    @Test
    public void testGetBooks(){
        Collection<Book> books = target("/books").request().get(new GenericType<Collection<Book>>(){});
        Assert.assertNotNull(books);
        Assert.assertTrue(books.size() == 2);
    }
}
