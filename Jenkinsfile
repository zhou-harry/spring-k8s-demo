node {
   def selectedModule="${module}".split()
   stage('代码同步') {
      git branch: '${branch}', credentialsId: 'gitlab-root', url: 'http://k8s-master:8016/root/spring-k8s.git'
   }
   stage('Maven编译') {
      sh 'mvn clean package -Dmaven.test.skip=true'
   }
   stage('Docker构建') {
      for(int i=0;i<selectedModule.length;i++){
        def module=selectedModule[i];
        dir(module) {
          sh '''
            chmod 755 target/deploy/build.sh
            target/deploy/build.sh ${module}
          '''
        }
      }
   }
   stage('K8S发布') {
      for(int i=0;i<selectedModule.length;i++){
        def module=selectedModule[i];
        dir(module) {
          sh '''
            chmod 755 target/deploy/deploy.sh
            target/deploy/deploy.sh ${module}
          '''
        }
      }
   }
   stage('Jenkins归档') {
      for(int i=0;i<selectedModule.length;i++){
        def module=selectedModule[i];
        dir(module) {
          archiveArtifacts '**/target/deploy/*.jar'
        }
      }
   }
}