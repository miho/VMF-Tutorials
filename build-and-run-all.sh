#!/bin/sh
set -eu

repo_dir=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)

for tutorial in "$repo_dir"/VMF-Tutorial-*; do
    [ -d "$tutorial" ] || continue
    printf '\nBuilding and running %s\n' "${tutorial##*/}"
    (
        cd "$tutorial"
        if [ -f gradlew ]; then
            sh ./gradlew clean build run --console=plain
        elif [ -f pom.xml ]; then
            mvn -B -ntp clean verify
        else
            printf '%s\n' "No build found in $tutorial" >&2
            exit 1
        fi
    )
done
