package ar.com.laboratory.config;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@ApplicationScoped
@Priority(1)
public class SensitiveDataConfig {
    private static final Logger LOGGER = Logger.getLogger(SensitiveDataConfig.class.getName());

    @ConfigProperty(name = "list.of.sensitive.data", defaultValue = "")
    String sensitiveData;

    private List<String> sensitiveDataList;

    @PostConstruct
    public void init() {
        if (Objects.nonNull(sensitiveData) && !sensitiveData.isEmpty()) {
            LOGGER.info("Sensitive data list: " + sensitiveData);
            sensitiveDataList = Arrays.asList(sensitiveData.split(","));
        } else {
            sensitiveDataList = null;
        }
    }

    public List<String> getSensitiveDataList() {
        return sensitiveDataList;
    }
}