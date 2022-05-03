#!/bin/bash
rm *.class
javac WebClient.java Aggregator.java Application.java

java Application
