#!/bin/bash

APP_NAME=$1
PROFILE=$2
cd ${WORKSPACE}/config
kubectl apply configmap spring-k8s-server-config --from-file=${APP_NAME}-${PROFILE}.yaml
cd ${WORKSPACE}/config/secret
kubectl apply configmap ${APP_NAME}-secret --from-file=${APP_NAME}-secret.yaml
cd ${WORKSPACE}/${APP_NAME}/target/deploy
kubectl apply -f deploy.yaml