package com.ecard.service;

import com.ecard.entity.DrugSet;
import com.ecard.pojo.Response;

public interface DrugSetService {

    Response insert(DrugSet drugSet);

    Response update(DrugSet drugSet);

    Response selectInfo(Long drugSetNo);

    Response selectPostage(Long drugSetNo);

    Response selectZZXDList(Long drugNo, Integer page, Integer rows);

    Response select(String departmentName, Integer category, String keyword1, Integer version, Integer disableFlag, Long drugNo, Integer page, Integer rows);
}
