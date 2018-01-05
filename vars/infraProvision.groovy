def call() {

    configYaml = env.configYaml
    echo "From infraProvision"
    echo configYaml.getClass().toString()
    echo configYaml

    def cloudConfig = configYaml['infra']['cloud'] ?: null
    def baremetalConfig = configYaml['infra']['baremetal'] ?: null
    def containerConfig = configYaml['infra']['container'] ?: null

    if (cloudConfig) {
        echo cloudConfig
        provisionCloud(cloudConfig[0]['type'], cloudConfig[0]['count'])

//        cloudConfig.each { provisionCloud(it.type, it.count) }
//        for ( config in cloudConfig ) {
//            if (config.type == 'aws') {
//                provisionCloud(config.type, config.count)
//            }
//        }
    }

    if (baremetalConfig) {

    }

    if (containerConfig) {

    }
}