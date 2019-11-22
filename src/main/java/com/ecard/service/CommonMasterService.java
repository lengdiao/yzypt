package com.ecard.service;

import com.ecard.pojo.Response;

public interface CommonMasterService {

    Response selectUsage();

    Response selectWay();

    Response selectExecWhen();

    Response selectDecoctionWay();
}
