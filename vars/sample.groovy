
def gitPull(url){
    sh "git clone ${url}"
    sh "ls -al"
}

def call(name){
    echo "Hi ${name}"
}