def codeanalysis(credentialsId, JAVA_HOME){

    withSonarQubeEnv(credentialsId: credentialsId){

        sh "JAVA_HOME=$JAVA_HOME ${MAVEN_HOME}/bin/mvn clean package sonar:sonar"
    }

}

def qualitygate(credentialsId){

    def qg = waitForQualityGate abortPipeline: false, credentialsId: credentialsId

    println "The Quality Gate profile Status:${qg.status}"
}


