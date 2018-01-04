def call(Map config){
    String count = config.containsKey('count') ? config.count : '1'
    String platformType = config.platformType ?: 'static'
    String machineType = config.machineType ?: 'cloud'
    String cloudProvider = null
    if ( machineType == 'cloud' ){
        cloudProvider = config.cloudProvider ?: 'aws'
    }

    if (platformType == 'static'){
        if (machineType == 'cloud'){
            if ( cloudProvider == 'aws'){
                echo count
                echo platformType
                echo machineType
                echo cloudProvider

                node{
                    stage("Deploy ${count} ${machineType} host(s) on ${cloudProvider}"){
                        ansiColor('xterm') {
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
                                    verbose: true
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