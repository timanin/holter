/*******************************************************************************
 * Copyright 2014 Sergey Timanin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

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
