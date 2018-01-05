@Grab('org.yaml:snakeyaml')

import org.yaml.snakeyaml.Yaml

def call(String filename="config"){
    Yaml parser = new Yaml()

    try{
        configYaml = parser.load(("${WORKSPACE}/${filename}.yaml" as File).text)
    } catch ( FileNotFoundException ){
        try{
            configYaml = parser.load(("${WORKSPACE}/${filename}.yml" as File).text)
        } catch ( FileNotFoundException e ){
            println(e)
            System.exit(1)

        }
    }
    env.configYaml = configYaml
    echo ${env.configYaml}
}