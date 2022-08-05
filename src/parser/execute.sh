#!/bin/bash

echo "Compiling Parser.java..."
javac -d ./../../bin/ Parser.java
if [ $? -eq 0 ]; then
    echo "Compilation successful."
else
    echo "Compilation failed."
    exit 1
fi

echo "Executing Parser.java..."
java -cp "./../../bin" parser.Parser
if [ $? -eq 0 ]; then
    echo "Execution successful."
else
    echo "Execution failed."
    exit 1
fi
