#!/bin/bash

APP_NAME=$1
cd $WORKSPACE/$APP_NAME/target/deploy
kubectl apply -f deploy.yaml