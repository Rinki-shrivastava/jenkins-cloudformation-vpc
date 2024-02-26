pipeline {
    agent {label 'node'}

    stages {
        stage('Build') {
            steps {
                sh '''
                sudo apt update
                sudo apt install awscli -y
                '''
            }
        }
        stage('Test') {
            steps {
                git branch: 'main', credentialsId: 'add-your-key', url: 'https://github.com/Rinki-shrivastava/jenkins-cft.git'
            }
        }
        stage('deploy') {
        steps {
                sh "aws cloudformation create-stack --stack-name my-jks-vpc-stack --template-body file://createvpc.yaml --region us-east-2 --capabilities CAPABILITY_NAMED_IAM"
            }
        }
    }
}
# to update use 
# sh "aws cloudformation update-stack --stack-name my-jks-vpc-stack --template-body file://createvpc.yaml --region us-east-2 --capabilities CAPABILITY_NAMED_IAM"
