#!/bin/sh
echo "Running script."
cd /home/arodriguez/dev/menu-planner/
java -jar ./target/scala-2.11/menu-planner-assembly-0.0.1.jar -Dorg.slf4j.simplelogger.defaultlog=trace org.ardlema.executor.Executor
