def mvntest(JAVA_HOME) {

    sh "JAVA_HOME=$JAVA_HOME ${MAVEN_HOME}/bin/mvn test"
}

def mvnverify(JAVA_HOME) {

    sh "JAVA_HOME=$JAVA_HOME ${MAVEN_HOME}/bin/mvn verify -DskipUnitTests"
}

def mvnbuild(JAVA_HOME){

    sh "JAVA_HOME=$JAVA_HOME ${MAVEN_HOME}/bin/mvn clean install"
}

/*
def mvntest(mavenhome, toolchain) {
    sh "${mavenhome}/mvn -Dmaven.toolchains.file:${toolchain} -Djdk-version=1.8 test"
}
def mvnverify(mavenhome, toolchain){
    sh "${mavenhome}/mvn -Dmaven.toolchains.file:${toolchain} -Djdk-version=1.8 verify -DskipUnitTests"
}
def mvnbuild(mavenhome, toolchain){
    sh "${mavenhome}/mvn -Dmaven.toolchains.file:${toolchain} -Djdk-version=1.8 clean install"
}
*/
