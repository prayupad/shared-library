#! /usr/bin/env groovy

def gitPull(url){
    sh "git clone ${url}"
    sh "ls -al"
}