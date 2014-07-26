package com.sergeytimanin.tools.jmxproxy;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.sergeytimanin.tools.jmxproxy.resources.JmxClientResource;
import com.sergeytimanin.tools.jmxproxy.health.TemplateHealthCheck;

public class JmxClientApplication extends Application<JmxClientConfiguration> {

	public static void main(String[] args) throws Exception {
		new JmxClientApplication().run(args);
	}

	@Override
	public String getName() {
		return "hello-world";
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
		final TemplateHealthCheck healthCheck =
				new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		environment.jersey().register(resource);
	}

}
