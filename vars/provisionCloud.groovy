def call(type, count) {
    println("Provisioning ${count} host(s) on ${type}")
    if (type.toLowerCase() == 'aws') {
        node{
            stage("Provisioning infra"){
                ansiColor('xterm') {
                    echo "Deploying ${machineType} host on ${cloudProvider}"
                    ansibleTower credential: '',
                            extraVars: '''---
                                    job: "${JOB_NAME}"
                                    build: "${BUILD_NUMBER}"
                                    count: "${count}"''',
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
}