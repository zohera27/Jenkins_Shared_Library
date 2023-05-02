def gitcheckout(Map repository){

    checkout([

        $class:'GitSCM',
        branches:[[name:repository.branch]],
        userRemoteConfigs:[[url:repository.url]]
    ])
}