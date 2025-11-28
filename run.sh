#!/usr/bin/env bash
set -e
rm -rf target
mkdir -p target/classes

# Find all java files and compile
files=$(find src -name "*.java")
if [ -z "$files" ]; then
  echo "No java sources found under src/"
  exit 1
fi

# compile
javac -d target/classes $files

# run
java -cp target/classes app.Main
