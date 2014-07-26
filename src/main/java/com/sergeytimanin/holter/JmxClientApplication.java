package com.sergeytimanin.holter;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.sergeytimanin.holter.health.TemplateHealthCheck;
import com.sergeytimanin.holter.resources.*;

public class JmxClientApplication extends Application<JmxClientConfiguration> {

	public static void main(String[] args) throws Exception {
		new JmxClientApplication().run(args);
	}

	@Override
	public String getName() {
		return "test-app-name";
	}

	@Override
	public void initialize(Bootstrap<JmxClientConfiguration> bootstrap) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run(JmxClientConfiguration configuration, Environment environment) throws Exception {
		final JmxClientResource resource = new JmxClientResource(
				configuration.getTemplate(),
				configuration.getDefaultName()
				);
		final JmxClientResource2 resource2 = new JmxClientResource2(
				configuration.getTemplate(),
				configuration.getDefaultName()
				);
		final TemplateHealthCheck healthCheck =
				new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
		environment.jersey().register(resource2);
	}

}
