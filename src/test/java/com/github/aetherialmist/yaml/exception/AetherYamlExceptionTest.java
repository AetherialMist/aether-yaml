package com.github.aetherialmist.yaml.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AetherYamlExceptionTest {

    @Test
    void coverage() {
        AetherYamlException exception = new AetherYamlException();
        assertThat(exception).isNotNull();

        exception = new AetherYamlException("Message");
        assertThat(exception).isNotNull();

        exception = new AetherYamlException("Message", new RuntimeException("Test Exception"));
        assertThat(exception).isNotNull();

        exception = new AetherYamlException(new RuntimeException("Test Exception"));
        assertThat(exception).isNotNull();
    }
}