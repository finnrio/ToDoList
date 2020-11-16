pipeline {
    agent any
    stages {
        stage("Test"){
            steps {
                echo "Hello world..."
                java test/MainTest.java
            }
        }
        stage("Build"){
            steps {
                echo "Building build."
            }
        }
    }
}