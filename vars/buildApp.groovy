// vars/buildApp.groovy

def call(String appName) {
    echo "Building ${appName}..."
    
    // Clone the Git repository
    sh "git clone https://github.com/your-repo/${appName}.git"
    
    // Navigate to the cloned directory
    dir("${appName}") {
        // Install dependencies
        sh "npm install"
        
        // Build the application
        sh "npm run build"
    }
    
    // Clean up the cloned repository
    sh "rm -rf ${appName}"
}
