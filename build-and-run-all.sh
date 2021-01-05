#!/bin/bash

# get script location
# see https://stackoverflow.com/a/246128
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"

for d in */; do
    if [[ $d == VMF-Tutorial* ]]; then 
        echo "----------------------------------------"
        echo " > compile & run project $d"; 
        echo "----------------------------------------"
        cd $DIR
        cd $d
        if [ -f "./gradlew" ]; then
          ./gradlew clean run --stacktrace
        else 
          echo "gradle wrapper not deteted. no project detected. skipping."
        fi
    fi
done

