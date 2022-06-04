package com.github.aetherialmist.yaml;

import com.github.aetherialmist.yaml.data.Data;
import com.github.aetherialmist.yaml.data.DataTwo;
import com.github.aetherialmist.yaml.data.NestedData;
import com.github.aetherialmist.yaml.exception.AetherYamlException;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AetherYamlTest {

    private static final String VALID_YAML = "./test-0.yaml";
    private static final String INVALID_YAML = "./test-1.yaml";
    private static final String DOES_NOT_EXIST_YAML = "./does-not-exist.yaml";

    @Test
    void readFileSuccess() throws AetherYamlException {
        Data data = AetherYaml.readFile(AetherYaml.getFileFromResources(VALID_YAML), Data.class);

        assertDataZero(data);
    }

    @Test
    void readFileFileNotFound() {
        assertThrows(AetherYamlException.class, () -> AetherYaml.getFileFromResources(DOES_NOT_EXIST_YAML));
    }

    @Test
    void readFileInvalidFormat() throws AetherYamlException {
        File file = AetherYaml.getFileFromResources(INVALID_YAML);
        assertThrows(AetherYamlException.class, () -> AetherYaml.readFile(file, Data.class));
    }

    @Test
    void readFileExplicitYaml() throws AetherYamlException {
        Yaml yaml = AetherYaml.createParser(Data.class);
        Data data = AetherYaml.readFile(AetherYaml.getFileFromResources(VALID_YAML), yaml);

        assertDataZero(data);
    }

    @Test
    void readFileExplicitWrongYaml() throws AetherYamlException {
        Yaml yaml = AetherYaml.createParser(DataTwo.class);
        File file = AetherYaml.getFileFromResources(VALID_YAML);

        assertThrows(AetherYamlException.class, () -> AetherYaml.readFile(file, yaml));
    }

    private void assertDataZero(Data data) {
        assertThat(data).isNotNull();
        assertThat(data.getStringKey()).isEqualTo("value-0 pine tree");

        NestedData nestedData = data.getNestedData();
        assertThat(nestedData).isNotNull();
        assertThat(nestedData.getIntKey()).isEqualTo(1234);
        assertThat(nestedData.getStringListKey()).hasSize(3);
    }

}