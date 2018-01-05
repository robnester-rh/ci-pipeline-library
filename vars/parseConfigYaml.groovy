@Grab('org.yaml:snakeyaml')

import org.yaml.snakeyaml.Yaml

def call(String filename="config"){
    Yaml parser = new Yaml()

    try{
        def configYaml = parser.load(("${WORKSPACE}/${filename}.yaml" as File).text)
        env.configYaml = configYaml
    } catch ( FileNotFoundException ){
        try{
            def configYaml = parser.load(("${WORKSPACE}/${filename}.yml" as File).text)
            env.configYaml = configYaml
        } catch ( FileNotFoundException e ){
            println(e)
            System.exit(1)

        }
    }
    echo env.configYaml
}