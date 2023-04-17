def callDockerBuild(String imageName, String dockerfilePath, String contextPath) {
    try {
        // Change to the context path directory
        dir(contextPath) {
            // Run the Docker build command
            sh "docker build -t ${imageName} -f ${dockerfilePath} ."
        }
    } catch (Exception e) {
        // Handle any errors that occur during the Docker build
        error "Failed to build Docker image: ${e.message}"
    }
}
