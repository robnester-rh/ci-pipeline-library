def call() {

    echo "From infraProvision"
    echo env.configYaml.getClass().toString()
    echo env.configYaml


//    def cloudConfig = env.configYaml['infra']['cloud'] ?: null
//    def baremetalConfig = env.configYaml['infra']['baremetal'] ?: null
//    def containerConfig = env.configYaml['infra']['container'] ?: null
//
//    if (cloudConfig) {
//        echo cloudConfig
//        provisionCloud(cloudConfig[0]['type'], cloudConfig[0]['count'])
//
//        cloudConfig.each { provisionCloud(it.type, it.count) }
//        for ( config in cloudConfig ) {
//            if (config.type == 'aws') {
//                provisionCloud(config.type, config.count)
//            }
//        }
//    }
//
//    if (baremetalConfig) {
//
//    }
//
//    if (containerConfig) {
//
//    }
}