package com.example.verify.service;

import com.example.verify.config.MappingConfig;
import com.example.verify.exception.MappingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class MappingService {

    private final MappingConfig mappingConfig;

    public Map<String, Object> requestConvert(Object object) {
        List<MappingConfig.MappingModel> mapper = mappingConfig.getMappingItems();
        Map<String, Object> resultMap = new HashMap<>(mapper.size());
        if (mapper.size() == 0) {
            log.error("未配置 mapping-items ，请在application.yml中配置映射关系");
            return resultMap;
        }
        mapper.forEach(x -> resultMap.put(x.getTarget(), getRetrievedValue(object, x)));
        return resultMap;
    }

    private Object getRetrievedValue(Object object, MappingConfig.MappingModel x) {

        Object retrievedValue = null;
        String source = x.getSource();
        try {
            retrievedValue = PropertyUtils.getProperty(object, source);
        } catch (Exception e) {
            if (!(e instanceof NoSuchMethodException)) {
                throw new MappingException(x.getSource(), e);
            }
        }

        if (StringUtils.isEmpty(retrievedValue)) {
            if (StringUtils.hasText(x.getDefaultValue())) {
                log.debug("获取默认值：[]", x.getDefaultValue());
                retrievedValue = x.getDefaultValue();
            }
            if (x.isRequired() && StringUtils.isEmpty(retrievedValue)) {
                throw new MappingException(x.getSource(), "必输字段为空");
            }
        }

        return retrievedValue;
    }
}