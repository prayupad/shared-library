def cloneSCM(url){
    checkout scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[url: "${url}"]])
}