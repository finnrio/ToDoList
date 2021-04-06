pipeline {
    agent any

    stages {
        stage("Build"){
            steps {
                echo "Building"
                sh "rm -rf build"
                sh "mkdir build"
                sh "touch build/car.txt"
                sh "echo 'chassisesse' > build/car.txt"
            }
        }
        stage("Test"){
            steps{
                sh "test -f build/car.txt"
                sh "grep 'chassis' build/car.txt"
            }
        }
        stage("Publish"){
            steps{
                archiveArtifacts artifacts: "build/"
            }
        }
    }
}