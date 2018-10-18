#!/usr/bin/env bash
# @author   fankai
# @date     Jul 16 20:55:11 HKT 2018

javac TestGC.java
java -cp . -XX:+UseSerialGC -Xmx10M -XX:+PrintGC TestGC 600000

