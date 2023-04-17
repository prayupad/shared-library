def deployToKubernetes(String appName){
    stage('Deploy to kubernetes')
      steps {
        sh "kubectl apply -f deployment.yml"
      }
    }
}
