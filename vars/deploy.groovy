def deployToKubernetes(String appName, imageName){
    stage('Deploy to kubernetes')
      steps {
        sh "kubectl apply -f deployment.yml"
      }
    }
}
