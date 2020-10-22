package casia.isiteam.sensitivedetection.service;

import casia.isiteam.sensitivedetection.model.SensitiveWord;

import java.util.Collection;
import java.util.List;

public interface SensitiveWordService {

    List<SensitiveWord> findAllKeywords();

    void saveKeywords(Collection<SensitiveWord> keywords);
}
