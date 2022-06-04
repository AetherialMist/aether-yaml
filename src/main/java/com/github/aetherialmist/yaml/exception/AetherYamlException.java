package com.github.aetherialmist.yaml.exception;

@SuppressWarnings("unused")
public class AetherYamlException extends Exception {

    public AetherYamlException() {
        super();
    }

    public AetherYamlException(String message) {
        super(message);
    }

    public AetherYamlException(String message, Throwable cause) {
        super(message, cause);
    }

    public AetherYamlException(Throwable cause) {
        super(cause);
    }
}
