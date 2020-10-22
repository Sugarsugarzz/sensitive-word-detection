package casia.isiteam.sensitivedetection.mapper;

import casia.isiteam.sensitivedetection.model.SensitiveWord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Mapper
@Repository
public interface SensitiveWordMapper {

    List<SensitiveWord> findAllKeywords();

    void saveKeywords(@Param("words") Collection<SensitiveWord> words);
}
