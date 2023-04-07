def gitCheckout(url){
    checkout scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[url: "${url}"]])
    sh "Code Cloned Successfully !"
}

def nodeJsBuild(){
    sh "npm install"
    echo "Build Successfully !"
}

def nodeJsTest(){
    sh "npm run test"
    echo "Test Successfully !"
}