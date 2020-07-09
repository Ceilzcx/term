package com.example.term.service;

import com.example.term.entity.DangerEntity;
import com.example.term.form.DangerForm;
import com.example.term.vo.DangerPhotoVo;
import com.example.term.vo.DangerVo;

import java.util.List;

public interface IDangerService {

    DangerPhotoVo createDanger(DangerForm dangerForm);

    List<DangerVo> getAllDanger(int eid);

    // 获得当前用户提交的所有隐患
    List<DangerVo> getDangersByUid(int uid);

    // 获得安管负责人操作的所有隐患(除合格状态外)
    List<DangerVo> getKeynoteDangers();

    // 获得安管员操作的所有隐患(除合格状态外)
    List<DangerVo> getNotKeynoteDangers();

    // 通过已整控和完成整控的隐患
    List<DangerVo> getRectDangers();


    DangerEntity getInfo(int did);

    DangerEntity updateStatus(int did, String status);

}
