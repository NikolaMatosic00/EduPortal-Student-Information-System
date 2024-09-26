package com.studentska.sluzba.service;

import com.studentska.sluzba.dto.administrator.OveriSemestarDTOReq;

public interface SemestarService {

	void overiSemestar(String token, int semestarId);
}
