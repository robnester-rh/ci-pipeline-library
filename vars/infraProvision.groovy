def call(configYaml) {

    def cloudConfig = configYaml['infra']['cloud'] ?: null
    def baremetalConfig = configYaml.infra.baremetal ?: null
    def containerConfig = configYaml.infra.container ?: null

    if (cloudConfig) {
//        cloudConfig.each { provisionCloud(it.type, it.count) }
        for ( config in cloudConfig ) {
            if (config.type == 'aws') {
                provisionCloud(config.type, config.count)
            }
        }
    }

    if (baremetalConfig) {

    }

    if (containerConfig) {

    }
}