def applyk8sManifest()
    sh "kubectl apply -f deployment.yml"

def deployToKubernetes(name, appName){
    jobDsl scriptText: '''
        pipelineJob('''+"\"${name}\""+''') {
            definition{
                cps{
                    script(\'\'\'
                        @Library(\'shared-library-nilanjan\') _
                        pipeline{
                            agent{
                                label "ec2-slave"
                            }
                            stages{
                                stage("Deploy to kubernetes"){
                                    steps{
                                        script{
                                            deploy.applyk8sManifest()
                                        }
                                    }
                                }
                            }
                        }
                    \'\'\'.stripIndent())
                    sandbox()
                }
            }
        }'''.stripIndent()
}
