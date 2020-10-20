package casia.isiteam.sensitivedetection.service;

import java.util.Collection;
import java.util.List;

public interface SensitiveWordService {

    List<String> findAllKeywords();

    void saveKeywords(Collection<String> keywords);
}
