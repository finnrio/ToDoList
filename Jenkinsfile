pipeline {
    agent any
    stages {
        stage("Test"){
            steps {
                echo "Hello world..."
                echo "This is build $BUILD_NUMBER of demo $DEMO"
            }
        }
        stage("Build"){
            steps {
                echo "Building build."
            }
        }
    }
}