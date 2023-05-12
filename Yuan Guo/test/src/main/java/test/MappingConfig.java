package test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "mapping")
public class MappingConfig {

    private List<MappingBo> list;

    public List<MappingBo> getList() {
        return list;
    }

    public void setList(List<MappingBo> list) {
        this.list = list;
    }
}
