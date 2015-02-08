package com.sergeytimanin.holter.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.ClassRule;
import org.junit.Test;

import com.sergeytimanin.holter.HolterApplication;
import com.sergeytimanin.holter.HolterConfiguration;
import com.sergeytimanin.holter.core.JmxClient;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.*;



public class GetItemPlainTest {
  private static final String configPath = "src/dist/settings.yml";

  @ClassRule
  public static final DropwizardAppRule<HolterConfiguration> RULE =
      new DropwizardAppRule<HolterConfiguration>(HolterApplication.class, configPath);

  @Test
  public void pingShouldReturnPong() {
    try {
      URL myURL = new URL("http://localhost:8082/admin/ping");
      URLConnection myURLConnection = myURL.openConnection();

      BufferedReader in =
          new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null)
        assertThat("pong").isEqualTo(inputLine);
      in.close();
    } catch (MalformedURLException e) {
      // new URL() failed
      // ...
    } catch (IOException e) {
      // openConnection() failed
      // ...
    }
  }
}
