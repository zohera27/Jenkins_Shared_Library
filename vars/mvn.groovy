@Library('Jenkins_Shared_Library') _

pipeline{
    
    agent any

    environment{

        JAVA8_HOME = "${tool 'JAVA8'}"
        JAVA11_HOME = "${tool 'JAVA11'}"
        MAVEN_HOME = "${tool 'MAVEN'}"
    }

    parameters{
        
        choice(name: 'action', choices: 'create\ndelete', description: 'Choose Create/Destroy')
        string(name: 'ImageName', description: "Name of the Docker Build", defaultValue: 'javapp')
        string(name: 'ImageTag', description: "Tag of the Docker Build", defaultValue: 'v1')
        string(name: 'DockerHubUser', description: "Dokcer HUB User Account", defaultValue: 'zohera27')
        
    }

    stages{
        
        stage('Git Checkout') {

         when { expression { params.action == 'create' } }

            steps{

                script{

                    git.gitcheckout(
                        branch: "main",
                        url: "https://github.com/zohera27/devops_Java_Project.git"
                    )
                }
            }
        }

        stage('Unit Test maven') {

         when { expression { params.action == 'create' } }
        
            steps{

                script{

                    mvn.mvntest(JAVA8_HOME)
                }
            }
        }

        stage('Integration Test maven') {

         when { expression { params.action == 'create' } }

            steps{

                script{

                    mvn.mvnverify(JAVA8_HOME)
                }
            }
        }  

        stage('Maven Build : maven') {

         when { expression { params.action == 'create' } }
        
            steps{

                script{

                    mvn.mvnbuild(JAVA8_HOME)
                }
            }
        }

    }
}
