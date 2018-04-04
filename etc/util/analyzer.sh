#!/bin/bash
mkdir -p swagger-repo

for i in xchange-*; do pushd $i; 
[[ target/jaxrs-analyzer/swagger.json ]] ||
java -XDebug -jar \
 ~/.m2/repository/com/sebastian-daschner/jaxrs-analyzer/0.17-SNAPSHOT/jaxrs-analyzer-0.17-SNAPSHOT.jar \
target/classes 
[[ target/jaxrs-analyzer/swagger.json ]] &&
cp target/jaxrs-analyzer/swagger.json ../swagger-repo/${i/xchange-/}.swagger.json
popd
done
