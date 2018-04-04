#!/bin/bash

#mkdir -p swagger-repo
#for i in xchange-*; do
i=$1
pushd $i;
mvn -T7  --fail-at-end install -DskipTests=true  jaxrs-analyzer:analyze-jaxrs
[[ target/jaxrs-analyzer/swagger.json ]] &&
cp target/jaxrs-analyzer/swagger.json ../swagger-repo/${i/xchange-/}.swagger.json
popd
#done
