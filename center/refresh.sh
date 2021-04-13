#!/bin/bash

APP_NAME=$1
PROFILE=$2
cd ${WORKSPACE}/center
kubectl apply -f app-crb.yaml
kubectl apply -f ./config/${APP_NAME}-${PROFILE}.yaml
kubectl apply -f ./secret/${APP_NAME}-secret.yaml
