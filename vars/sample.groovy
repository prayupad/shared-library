def call(name){
    echo "Hi ${name}"
}

def gitPull(url){
    sh "git clone ${url}"
    sh "ls -al"
}

