package casia.isiteam.sensitivedetection.service.impl;

import casia.isiteam.sensitivedetection.mapper.SensitiveWordMapper;
import casia.isiteam.sensitivedetection.model.SensitiveWord;
import casia.isiteam.sensitivedetection.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {

    @Autowired
    SensitiveWordMapper sensitiveWordMapper;

    @Override
    public List<String> findAllKeywords() {
        return sensitiveWordMapper.findAllKeywords();
    }

    @Override
    public void saveKeywords(Collection<String> keywords) {
        sensitiveWordMapper.saveKeywords(keywords);
    }
}
