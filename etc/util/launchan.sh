#!/usr/bin/env bash
rm *.iml

for i in xchange-*; do echo $i;done |grep -v .iml |grep  -v xchange-core | xargs -n1 -P7 bash ./etc/util/analyzer.sh