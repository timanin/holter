package com.sergeytimanin.holter;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.sergeytimanin.holter.health.TemplateHealthCheck;
import com.sergeytimanin.holter.resources.*;

public class HolterApplication extends Application<HolterConfiguration> {

	public static void main(String[] args) throws Exception {
		new HolterApplication().run(args);
	}

	@Override
	public String getName() {
		return "Holter";
	}

	@Override
	public void initialize(Bootstrap<HolterConfiguration> bootstrap) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run(HolterConfiguration configuration, Environment environment) throws Exception {
		final TemplateHealthCheck healthCheck =
				new TemplateHealthCheck(configuration.getTemplate());
		environment.healthChecks().register("template", healthCheck);
		
		final RetrieveItemResource getItemPlain = new RetrieveItemResource();
		environment.jersey().register(getItemPlain);
}

}
