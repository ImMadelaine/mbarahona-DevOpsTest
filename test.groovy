pipeline {
    agent any

    stages {
        stage('Clonar') {
            steps {
               script{
                sh 'git clone https://github.com/ImMadelaine/mbarahona-DevOpsTest.git'
               }
            }
        }
        stage('CrearImg'){
            steps {
               script{
                sh 'git clone https://github.com/ImMadelaine/mbarahona-DevOpsTest.git'
               }
            }
        }
    }
}
