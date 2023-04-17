def callKubectlApply(String kubeconfigPath, String manifestPath) {
    try {
        // Run the kubectl apply command
        sh "kubectl --kubeconfig=${kubeconfigPath} apply -f ${manifestPath}"
    } catch (Exception e) {
        // Handle any errors that occur during the kubectl apply
        error "Failed to apply Kubernetes manifest: ${e.message}"
    }
}
