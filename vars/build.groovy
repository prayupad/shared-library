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

def dockerBuildAndPush(dockerRegistry){
    withDockerRegistry(credentialsId: 'nilanjan-docker', url: "${dockerRegistry}") {
        sh "docker build -t iamnilanjan3/ecom-orders-api:${BUILD_NUMBER} ."
        sh "docker push iamnilanjan3/ecom-orders-api:${BUILD_NUMBER}"
    }
}