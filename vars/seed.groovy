def createDeploymentJob(jobName) {
    pipelineJob(jobName) {
        parameters{
            stringParam('repoURL','dockerRegistry','credentialsId', 'imageName' )
            
        }
        definition{
            cps{
                script('''
                    @Library('shared-library-nilanjan') _
                    pipeline{
                        agent{
                            label "ec2-slave"
                        }
                        stages{
                            stage("Get Sources"){
                                steps{
                                    script{
                                        build.gitCheckout("${repoURL}")
                                    }
                                }
                            }
                            stage("Install Node.Js"){
                                steps{
                                    script{
                                        build.installNodeJs()
                                    }
                                }
                            }
                            stage("Install NPM"){
                                steps{
                                    script{
                                        build.installNpm()
                                    }
                                }
                            }
                            stage("Build"){
                                steps{
                                    script{
                                        build.nodeJsBuild()
                                    }
                                }
                            }
                            stage("Test"){
                                steps{
                                    script{
                                        build.nodeJsTest()
                                    }
                                }
                            }
                            stage("Docker Build & Push"){
                                steps{
                                    script{
                                        build.dockerBuildAndPush("${dockerRegistry}","${credentialsId}","${imageName}")
                                    }
                                }
                            }
                            
                        }
                        
                    }
                ''').stripIndent()
                sandbox()
            }
        }
    }
}