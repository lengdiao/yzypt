package com.ecard.dao;

import com.ecard.entity.MrPicture;

public interface MrPictureMapper {
    int deleteByPrimaryKey(Long mrPictureNo);

    int insert(MrPicture record);

    int insertSelective(MrPicture record);

    MrPicture selectByPrimaryKey(Long mrPictureNo);

    int updateByPrimaryKeySelective(MrPicture record);

    int updateByPrimaryKey(MrPicture record);

    MrPicture[] selectByMedRecordNo(String medRecordNo);
}