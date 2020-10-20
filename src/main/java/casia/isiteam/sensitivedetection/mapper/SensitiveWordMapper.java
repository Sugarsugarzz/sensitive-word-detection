package casia.isiteam.sensitivedetection.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Mapper
@Repository
public interface SensitiveWordMapper {

    List<String> findAllKeywords();

    void saveKeywords(@Param("keywords") Collection<String> keywords);
}
