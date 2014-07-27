#!/bin/bash

echo "*** Testing the item ***"

echo "  > item is not provided:"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory&attribute=HeapMemoryUsage'

echo

echo "  > item is provided and correct"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory&attribute=HeapMemoryUsage&item=committed'

echo

echo "  > item is provided but not correct"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory&attribute=HeapMemoryUsage&item=committe'

echo

echo "  > item provided but empty as item="
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory&attribute=HeapMemoryUsage&item='

echo

echo "  > item provided but empty as item"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory&attribute=HeapMemoryUsage&item'

echo

echo "*** Testing the attribute ***"

echo "  > attribute is provided and correct"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory&attribute=HeapMemoryUsage'

echo

echo "  > attribute is provided and is not correct"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory&attribute=HeapMemoryUsag'

echo

echo "  > attribute is provided but empty as attribute="
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory&attribute='

echo

echo "  > attribute is provided but empty as attribute"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory&attribute'

echo

echo "  > attribute is not provided"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory'

echo

echo "*** Testing the object ***"

echo "  > object is provided and correct"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memory&attribute=HeapMemoryUsage'

echo

echo "  > object is provided and is not correct"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=java.lang:type=Memo&attribute=HeapMemoryUsage'

echo

echo "  > object is provided but empty as object="
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object=&attribute=HeapMemoryUsage'

echo

echo "  > object is provided but empty as object"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&object&attribute=HeapMemoryUsage'

echo

echo "  > object is not provided"
curl -s 'http://localhost:8082/holter/getItem/plain?service=service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi&attribute=HeapMemoryUsage'

echo
