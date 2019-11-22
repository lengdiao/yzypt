package com.ecard.service;

import com.ecard.entity.DrugSet;
import com.ecard.pojo.Response;

public interface DrugSetService {

    Response insert(DrugSet drugSet);

    Response update(DrugSet drugSet);

    Response select(Integer category, String keyword1, Integer disableFlag, Integer page, Integer rows);
}
