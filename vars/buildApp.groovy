// vars/buildApp.groovy

    
def installNodeJs(){
    sh "sudo apt install nodejs -y"
    // sh "node_version=$(node -v)"
    // echo "Success installed Node.js ${node_version}"
}
def installNpm(){
    sh "sudo apt install npm -y"
}
def gitCheckout(url){
    checkout scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[url: "${url}"]])
    echo "Code Cloned Successfully !"
}

def nodeJsBuild(){
    sh "npm install"
    echo "Build Successfully !"
}

def nodeJsTest(){
    sh "npm run test"
    echo "Test Successfully !"
}

def dockerBuildAndPush(dockerRegistry,credentialsId,imageName){
    withDockerRegistry(credentialsId: "${credentialsId}", url: "${dockerRegistry}") {
        sh "docker build -t ${imageName}:${BUILD_NUMBER} ."
        sh "docker push ${imageName}:${BUILD_NUMBER}"
    }
}

def build(name, repoURL, imageName){
    jobDsl scriptText: '''
        pipelineJob('''+"\"${name}\""+''') {
            definition{
                cps{
                    script(\'\'\'
                        @Library(\'shared-library-prayashi\') _
                        pipeline{
                            agent any
                            }
                            stages{
                                stage("Get Sources"){
                                    steps{
                                        script{
                                            build.gitCheckout('''+"\"${repoURL}\""+''')
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
                                            build.dockerBuildAndPush("https://index.docker.io/v1/","prayashinag",'''+"\"${imageName}\""+''')
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
    

