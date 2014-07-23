package com.sergeytimanin.tools.jmxproxy;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxClient {

	public static void main(String[] args) throws Exception {
		
		// check the number of arguments
		if (args.length < 3) {
			System.out.println("Usage: java com.sergeytimanin.tools.jmxproxy.JmxClient URL ObjectName AttributeName");
			System.exit(0);
		}
		
		String jmxUrl        = args[0];
		String objectName    = args[1];
		String attributeName = args[2];
		String itemName	     = null;
		if (args.length == 4) { 
			itemName  = args[3];
		}
		
		JmxStuff jmx = new JmxStuff();
		jmx.getJmxItem(jmxUrl, objectName, attributeName, itemName);
				
	}
}

class JmxStuff {
	
	public void getJmxItem(String jmxUrl, String objectName, String attributeName, String itemName) throws Exception {
		// creating an RMI connector client to allow the JMX client to interact
		// with the JMX agent as if they were running on the same machine
		System.out.println("Creating a connection to: " + jmxUrl);
		JMXServiceURL url = new JMXServiceURL(jmxUrl);
		JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

		// Get an MBeanServerConnection
		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
		
		// Construct the ObjectName
		ObjectName mbeanName = new ObjectName(objectName);

//		System.out.println(mbeanName);
			
		try {
			Object returnedObject = mbsc.getAttribute(mbeanName, attributeName);
			
			Object printObj = new Object();
	        if (itemName != null && itemName != "" && returnedObject instanceof CompositeDataSupport) {
	            CompositeData compositeDataObject = (CompositeData) returnedObject;
	            printObj = compositeDataObject.get(itemName);
	//            return compositeDataObject.get(itemName);
	        } else {
	        	printObj = returnedObject;
	//            return returnedObject;
	        }
	
			
			System.out.println(objectName + "," + attributeName + " == " + printObj);
			
	//		CompositeData cd = (CompositeData) o;
	//		System.out.println(cd.get("committed"));
		}
		catch (Exception exc) {
			System.out.println("Error:");
			throw exc;
		}
		finally {
			jmxc.close();
		}
	}

}
