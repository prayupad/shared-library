// vars/deployApp.groovy

def call(String appName, String environment) {
    echo "Deploying ${appName} to ${environment}..."
    
    // Clone the Git repository
    sh "git clone https://github.com/your-repo/${appName}.git"
    
    // Navigate to the cloned directory
    dir("${appName}") {
        // Install dependencies
        sh "npm install"
        
        // Build the application
        sh "npm run build"
        
        // Deploy the application
        sh "kubectl apply -f ${appName}.yaml" // Assuming you are using Kubernetes for deployment
    }
    
    // Clean up the cloned repository
    sh "rm -rf ${appName}"
    
    // Run application tests
    echo "Running tests for ${appName}..."
    // Add your test steps here, e.g. running unit tests, integration tests, etc.
    // Example:
    sh "npm test"
    // Additional test steps as needed
}
