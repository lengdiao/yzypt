package com.ecard.service;

import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.RoleQr;

public interface RoleService {

    Response list(String roleName, Integer page, Integer rows);

    Response selectByNo(Long roleNo);

    Response save(RoleQr roleQr);

    Response update(RoleQr roleQr);

    Response delete(Long roleNo);

    Response authorityList();
}
