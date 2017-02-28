#!/bin/sh
cd /home/dev/menu-planner/
java -jar ./target/scala-2.11/menu-planner-assembly-0.0.1.jar sender=user1@gmail.com password=12345 recipients=user2@gmail.com rootpath=/home/user/menu-planner/ org.ardlema.executor.Executor
