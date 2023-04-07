def installNodeJs(){
    sh "sudo apt install nodejs"
    sh "node_version=$(node -v)"
    echo "Success installed Node.js ${node_version}"
}
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