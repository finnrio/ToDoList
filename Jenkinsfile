pipeline {
    agent any

    stages {
        stage("Build"){
            steps {
                echo "Building"
                sh "mkdir build"
                sh "touch build/car.txt"
                sh "echo 'chassis' > build/car.txt"
            }
        }
    }
}