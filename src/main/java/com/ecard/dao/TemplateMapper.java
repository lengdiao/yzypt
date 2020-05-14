package com.ecard.dao;

import com.ecard.entity.Template;
import com.ecard.pojo.queryResult.TemplateQr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TemplateMapper {
    int deleteByPrimaryKey(Long templateId);

    int insert(Template record);

    int insertSelective(Template record);

    Template selectByPrimaryKey(Long templateId);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);

    List<TemplateQr> selectByDrNoTypeName(
            @Param("drNo") Long drNo,
            @Param("dNo") String dNo,
            @Param("templateType") Integer templateType,
            @Param("templateName") String templateName);

    List<Template> selectByDrNoDNo(
            @Param("drNo") Long drNo,
            @Param("dNo") String dNo);

    void deleteByTemplateNo(Long templateNo);

    List<TemplateQr> selectByTemplateNo(Long templateNo);

    Long selectMaxTemplateNo();
}