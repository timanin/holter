#!/bin/bash

JAVA=java

if test -n "$JAVA_HOME"; then
    java="$JAVA_HOME/bin/java"
fi

exec "$java" -jar jmx-proxy-*.jar "$@"
