package com.sergeytimanin.holter.core;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.InvalidKeyException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JmxClient {
	private static Logger logger;

	public JmxClient() {
		logger = LoggerFactory
				.getLogger("com.sergeytimanin.holter.core.JmxClient");
		logger.info("Logger is starting. JmxClient is created.");
	}

	public Object getJmxItem(String serviceUrl, String objectName,
			String attributeName) throws AttributeNotFoundException,
			MBeanException, MalformedObjectNameException,
			InstanceNotFoundException, MalformedURLException, IOException,
			Exception {

		try (JMXConnector jmxc = JMXConnectorFactory.connect(new JMXServiceURL(
				serviceUrl), null)) {

			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

			ObjectName mbeanName = new ObjectName(objectName);

			Object returnedObject = mbsc.getAttribute(mbeanName, attributeName);

			if (returnedObject instanceof CompositeDataSupport) {
				CompositeData compositeDataObject = (CompositeData) returnedObject;
				return compositeDataObject.values().toString();
			} else {
				return returnedObject;
			}
		} catch (Exception exc) {
			logger.error("Exception caught: " + exc.getMessage());
			return exc.getMessage();
		}
	}

	public Object getJmxItem(String serviceUrl, String objectName,
			String attributeName, String itemName) throws InvalidKeyException,
			AttributeNotFoundException, MBeanException,
			MalformedObjectNameException, InstanceNotFoundException,
			MalformedURLException, IOException, Exception {

		try (JMXConnector jmxc = JMXConnectorFactory.connect(new JMXServiceURL(
				serviceUrl), null)) {

			MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

			ObjectName mbeanName = new ObjectName(objectName);

			Object returnedObject = mbsc.getAttribute(mbeanName, attributeName);

			if (returnedObject instanceof CompositeDataSupport) {
				// logger.error("item name info:");
				// if (itemName.isEmpty()) {
				// logger.error("item name is empty");
				// }
				// if (itemName == "") {
				// logger.error("item name == \"\"");
				// }
				// logger.error("item name length is " + itemName.length());

				CompositeData compositeDataObject = (CompositeData) returnedObject;

				if (!itemName.isEmpty()) {
					return compositeDataObject.get(itemName);
				} else {
					return compositeDataObject.values().toString();
				}
			} else {
				return returnedObject;
			}
		} catch (Exception exc) {
			logger.error("Exception caught: " + exc.getMessage());
			return exc.getMessage();
		}
	}
}
