package com.github.aetherialmist.yaml;

import com.github.aetherialmist.yaml.exception.AetherYamlException;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

@Slf4j
public final class AetherYaml {

    private AetherYaml() {
    }

    public static Yaml createParser(Class<?> clazz) {
        return new Yaml(new Constructor(clazz));
    }

    public static <T> T readFile(File file, Class<T> type) throws AetherYamlException {
        return readFile(file, createParser(type));
    }

    public static <T> T readFile(File file, Yaml yaml) throws AetherYamlException {
        T data;

        if (!file.exists()) {
            log.debug("File does not exist: {}", file.getAbsolutePath());
            return null;
        }

        try (Reader reader = new FileReader(file)) {
            data = yaml.load(reader);
        } catch (FileNotFoundException e) {
            throw new AetherYamlException("File not found.", e);
        } catch (IOException e) {
            throw new AetherYamlException("Error reading file (IO).", e);
        } catch (Exception e) {
            log.debug("The given 'org.yaml.snakeyaml.Yaml' may be of the wrong Class constructor.");
            throw new AetherYamlException("Error reading file (non-IO). Likely incompatible or incorrect types.", e);
        }

        return data;
    }

    /**
     * @param path The file path and name from the resources folder.
     * @return The {@link File} at the specified path.
     * @throws AetherYamlException If the file does not exist or there was
     *                             an error parsing the file's URI.
     */
    public static File getFileFromResources(String path) throws AetherYamlException {
        URL resource = AetherYaml.class.getClassLoader().getResource(path);
        if (resource == null) {
            throw new AetherYamlException("File not found: " + path);
        }

        try {
            return new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new AetherYamlException("Error with file URL.", e);
        }
    }

}
