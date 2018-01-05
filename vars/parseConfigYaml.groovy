@Grab('org.yaml:snakeyaml')

import org.yaml.snakeyaml.Yaml

def call(String filename="config"){
    Yaml parser = new Yaml()

    try{
        return parser.load(("${WORKSPACE}/${filename}.yaml" as File).text)
        echo "Found ${filename}.yaml"


    } catch ( FileNotFoundException ){
        try{
            return parser.load(("${WORKSPACE}/${filename}.yml" as File).text)
            echo "Found ${filename}.yml"

        } catch ( FileNotFoundException e ){
            println(e)
            System.exit(1)

        }
    }
}
