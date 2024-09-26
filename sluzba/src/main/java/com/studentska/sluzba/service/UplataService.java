package com.studentska.sluzba.service;

import com.studentska.sluzba.dto.administrator.EvidencijaUplataDTOReq;
import com.studentska.sluzba.dto.administrator.EvidencijaUplataDTORes;

import java.util.List;

public interface UplataService {
    List<EvidencijaUplataDTORes> uplate();

    void evidentirajUplatu(String token, EvidencijaUplataDTOReq req);

    List<EvidencijaUplataDTORes> uplateZaStudenta(String token);
}
