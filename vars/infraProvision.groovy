def call(configYaml) {

    def cloudConfig = configYaml['infra']['cloud'] ?: null
    def baremetalConfig = configYaml.infra.baremetal ?: null
    def containerConfig = configYaml.infra.container ?: null

    if (cloudConfig) {
        cloudConfig.each { provisionCloud(it.type, it.count) }

    }

    if (baremetalConfig) {

    }

    if (containerConfig) {

    }
}

def provisionCloud(type, count) {
    println("Provisioning ${count} host(s) on ${type}")
    if (type.toLowerCase() == 'aws') {
        for ( i in count.toInteger() ) {
            node{
                stage("Provisioning infra"){
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
    }
}