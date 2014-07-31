/*******************************************************************************
 * Copyright 2014 Sergey Timanin
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *******************************************************************************/

package com.sergeytimanin.holter.resources;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import java.util.concurrent.atomic.AtomicLong;

import com.sergeytimanin.holter.core.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/getItem")
public class RetrieveItemResource {
  private final AtomicLong counter;
  private static Logger logger;

  public RetrieveItemResource() {
    this.counter = new AtomicLong();

    logger = LoggerFactory.getLogger("com.sergeytimanin.holter.resources.RetrieveItemResource");
    logger.info("RetrieveItemResource Logger is starting.");

  }

  @GET
  @Path("/plain")
  @Timed
  @Produces(MediaType.TEXT_PLAIN)
  public String sayHelloPlain(@QueryParam("service") String service,
      @QueryParam("object") String object, @QueryParam("attribute") String attribute,
      @QueryParam("item") Optional<String> item, @QueryParam("debug") Optional<Boolean> debug)
      throws Exception {

    String debugInfo = null;
    if (debug.isPresent() && debug.get().equals(true)) {
      debugInfo =
          " * service: " + service + "\n * object: " + object + "\n * attribute: " + attribute
              + "\n * item: " + item.or("not set") + "\n * value(s): ";
    }

    JmxClient jmxc = new JmxClient();
    String jmxValue = new String();
    if (!item.isPresent() || item.get().isEmpty()) {
      jmxValue = jmxc.getJmxItem(service, object, attribute).toString();
    } else {
      jmxValue = jmxc.getJmxItem(service, object, attribute, item.get()).toString();
    }

    if (debug.isPresent() && debug.get().equals(true)) {
      return new String(debugInfo + jmxValue);
    } else {
      return new String(jmxValue);
    }
  }

  @GET
  @Path("/json")
  @Timed
  @Produces(MediaType.APPLICATION_JSON)
  public String sayHelloJson(@QueryParam("name") Optional<String> name) {
    final String value = "Hello json! My name is " + name.or("unknown");
    return new String(value);
  }

}
