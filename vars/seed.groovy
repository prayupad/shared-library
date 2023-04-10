@Library('shared-library-nilanjan') _

def createDeploymentJob(jobName) {
    pipelineJob(jobName) {
        steps{
            script{
                build.installNodeJs()
            }
        }
    }
}