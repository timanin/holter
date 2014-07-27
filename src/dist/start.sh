#!/bin/bash

CONFIG_FILENAME="settings.yml"

# Safely determines the base directory of the application
pushd $(dirname $0) >/dev/null
TMPDIR=$(pwd)
pushd $(dirname ${TMPDIR}) >/dev/null
BASEDIR=$(pwd)
popd >/dev/null
echo "Setting base dir to: ${BASEDIR}"

JAVA_HOME=${BASEDIR}/jdk7

if test -n "${JAVA_HOME}"; then
    java="${JAVA_HOME}/bin/java"
fi

exec "$java" -Done-jar.silent=true -jar ${BASEDIR}/bin/holter-*-standalone.jar server ${BASEDIR}/conf/${CONFIG_FILENAME} 2>&1 >/dev/null &
