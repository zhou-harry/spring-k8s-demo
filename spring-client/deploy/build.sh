#!/usr/bin/env bash
#定义变量
APP_NAME=$1
PROFILE=$2
API_VERSION="0.0.1-SNAPSHOT"
TAG=dev-$(date +%m%d%H%M)-${BUILD_NUMBER}
#如果下面需要将镜像推送到镜像仓库，那么此处的镜像名称需要带上镜像仓库地址
IMAGE_NAME="harry:8015/harry_rep/${APP_NAME}:$TAG"
#进入target目录复制Dockerfile文件
cd ${WORKSPACE}/${APP_NAME}/target/deploy
#构建docker镜像
docker build -t ${IMAGE_NAME} .
#登录Harbor镜像仓库（此处的仓库地址需要与上面IMAGE_NAME的仓库地址保持一致否则推送denied）
docker login -u admin -p Harbor12345 harry:8015
#推送docker镜像到Harbor镜像仓库
docker push ${IMAGE_NAME}
#删除本机镜像
docker rmi ${IMAGE_NAME}
#将部署文件发送到k8s服务器
sed -i "s@{artifactId}@${APP_NAME}@g" deploy.yaml
sed -i "s@{image:v}@${IMAGE_NAME}@g" deploy.yaml
sed -i "s@{profile}@${PROFILE}@g" deploy.yaml
#如果Jenkins服务器与k8s服务器不在同一台机器上
#mv deploy.yaml $API_NAME.yaml
#scp $API_NAME.yaml deploy.sh 192.168.64.128:/root/deploy/