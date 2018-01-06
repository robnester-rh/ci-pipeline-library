import groovy.json.*
import org.centos.pipeline.PipelineUtils

@NonCPS
def call() {
    def parser = new JsonSlurperClassic()
    def configJSON = parser.parseText(env.configJSON.toString())
    def pipelineUtils = new PipelineUtils()

    def cloudConfig = configJSON.infra.cloud ?: null
    def baremetalConfig = configJSON.infra.baremetal ?: null
    def containerConfig = configJSON.infra.container ?: null

    if (cloudConfig) {//
        for ( config in cloudConfig ) {
            if (config.type == 'aws') {
                String type = config.type
                String count = config.count
                pipelineUtils.provisionCloud(type, count)
            }
        }
    }
//
//    if (baremetalConfig) {
//
//    }
//
//    if (containerConfig) {
//
//    }
}