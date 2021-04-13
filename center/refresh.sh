#!/bin/bash

APP_NAME=$1
PROFILE=$2
cd ${WORKSPACE}/center
kubectl apply configmap ${APP_NAME}-config --from-file=/config/${APP_NAME}-${PROFILE}.yaml
kubectl apply configmap ${APP_NAME}-secret --from-file=/secret/${APP_NAME}-secret.yaml
