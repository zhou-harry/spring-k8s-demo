node {
   def selectedModule="${module}".split()
   stage('代码同步') {
      git branch: '${branch}', credentialsId: 'gitlab-root', url: 'git://github.com/zhou-harry/spring-k8s-demo.git'
   }
   stage('Maven编译') {
      sh 'mvn clean package -Dmaven.test.skip=true -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true'
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