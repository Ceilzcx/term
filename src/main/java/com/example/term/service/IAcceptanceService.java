package com.example.term.service;

import com.example.term.entity.AcceptanceEntity;
import com.example.term.form.AcceptanceForm;

public interface IAcceptanceService {

    AcceptanceEntity getInfo(int id);
    // 通过隐患id获得entity
    AcceptanceEntity getInfoByRid(int rid);

    AcceptanceEntity createAcceptance(AcceptanceForm form);

}
