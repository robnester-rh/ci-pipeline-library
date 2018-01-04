def call(Map config){
    String platformType = config.platformType ?: 'static'
    String machineType = config.machineType ?: 'cloud'
    String cloudProvider = null
    if ( machineType == 'cloud' ){
        cloudProvider = config.cloudProvider ?: 'aws'
    }

    if (platformType == 'static'){
        if (machineType == 'cloud'){
            if ( cloudProvider == 'aws'){

                node{
                    stage("Provision infra"){
                        ansiColor('xterm') {
                            echo "Deploying ${machineType} host on ${cloudProvider}"
                            ansibleTower credential: '',
                                    extraVars: '''---
                                    job: "${JOB_NAME}"
                                    build: "${BUILD_NUMBER}"''',
                                    importTowerLogs: true,
                                    importWorkflowChildLogs: true,
                                    inventory: '',
                                    jobTags: '',
                                    jobTemplate: 'Provision RHEL on AWS',
                                    limit: '',
                                    removeColor: false,
                                    templateType: 'workflow',
                                    towerServer: 'Local Tower',
                                    verbose: false
                        }
                    }
                }
            }

        } else if ( machineType == 'baremetal') {

        } else {
            print("Invalid machine type")
        }

    } else if (platformType == 'dynamic'){

    } else {
        print("Invalid platform type")
    }
}