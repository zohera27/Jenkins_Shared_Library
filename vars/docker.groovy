def build(String ImageName, String ImageTag, String Dockeraccount){

    sh """

        docker image build -t ${Dockeraccount}/${ImageName} .
        docker image tag ${Dockeraccount}/${ImageName} ${Dockeraccount}/${ImageName}:${ImageTag}
        docker image tag ${Dockeraccount}/${ImageName} ${Dockeraccount}/${ImageName}:latest
    
    """

}

def ImageScan(String ImageName, String ImageTag, String Dockeraccount){
    
     sh """   
      trivy image ${Dockeraccount}/${ImageName}:latest > scan.txt
      cat scan.txt
     """
}

def PushImage(String ImageName, String ImageTag, String Dockeraccount){

    
    withCredentials([usernamePassword(
             credentialsId: "docker",
             usernameVariable: "USER",
             passwordVariable: "PASS"
    )]) {

        sh "docker login -u '$USER' -p '$PASS'"
    }

    sh "docker image push ${Dockeraccount}/${ImageName}:${ImageTag}"
    sh "docker image push ${Dockeraccount}/${ImageName}:latest"
    
}

def clean(String ImageName, String ImageTag, String Dockeraccount) {

    
    sh """

        docker rmi ${Dockeraccount}/${ImageName}:${ImageTag}
        docker rmi ${Dockeraccount}/${ImageName}:latest

    """
 
}
@NonCPS
def pull(String ImageName, String ImageTag, String Dockeraccount) {

    withCredentials([usernamePassword(
             credentialsId: "docker",
             usernameVariable: "USER",
             passwordVariable: "PASS"
    )]) {

        sh "docker login -u '$USER' -p '$PASS'"
    }

    sh "docker image push ${Dockeraccount}/${ImageName}:${ImageTag}"
    sh "docker image push ${Dockeraccount}/${ImageName}:latest"
    
}