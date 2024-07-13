pipeline {
    agent any

    environment  {
        DOCKER_IMAGE = "pythonImg:latest"
        DOCKER_REGISTRY = "my.jfrog.io/mbarahona-docker"
        DOCKER_CREDENTIALS_ID = "my-jfrog-credentials"
    }

    stages {
        stage('Clonar') {
            steps {
               script{
                sh 'git clone https://github.com/ImMadelaine/mbarahona-DevOpsTest.git'
               }
            }
        }
        stage('Construir'){
            steps {
               script{
                    sh "cd dockerImage"
                    sh "docker build -t ${DOCKER_IMAGE} ."
               }
            }
        }
         stage('Subir'){
            steps {
               script{
                    withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin ${DOCKER_REGISTRY}"
               }
               sh "docker tag ${DOCKER_IMAGE} ${DOCKER_REGISTRY}/${DOCKER_IMAGE}"
               sh "docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE}"
            }
        }
    }

    post {
        always {
            script {
                sh "docker rmi ${DOCKER_IMAGE} ${DOCKER_REGISTRY}/${DOCKER_IMAGE} || true"
            }
        }
    }
}
}
