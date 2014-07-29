#!/bin/bash

#*******************************************************************************
# Copyright 2014 Sergey Timanin
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#*******************************************************************************

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
