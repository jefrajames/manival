package org.jefrajames.manival.test;

import org.jefrajames.manival.schema.queries.HelloQuery;
import org.jefrajames.manival.schema.queries.HelloWithNameQuery;
import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HelloTest {

    private static String SERVER_URL = "http://localhost:8080/graphql";

    @Test
    public void testHello() {
        HelloQuery query = HelloQuery.builder().build();
        HelloQuery.Result result = query.request(SERVER_URL).post();
        assertNotNull(result.getHello());
        out.println("result.getHello=" + result.getHello());
        assertEquals("Hello", result.getHello());
    }

    @Test
    public void testHelloWithName() {
        HelloWithNameQuery query = HelloWithNameQuery.builder().withName("jefrajames").build();
        HelloWithNameQuery.Result result = query.request(SERVER_URL).post();
        assertNotNull(result.getHelloWithName());
        out.println("result.getHelloWithName=" + result.getHelloWithName());
        assertEquals("Hello jefrajames", result.getHelloWithName());
    }

}
