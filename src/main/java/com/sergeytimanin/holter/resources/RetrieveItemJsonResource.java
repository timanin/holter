package com.sergeytimanin.holter.resources;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.atomic.AtomicLong;

//@Path("/getItem")
//@Produces(MediaType.APPLICATION_JSON)
public class RetrieveItemJsonResource {
    private final AtomicLong counter;

    public RetrieveItemJsonResource() {
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public String sayHello(@QueryParam("name") Optional<String> name) {
        final String value = "Hello " + name.get();
        return new String(value);
    }

}
