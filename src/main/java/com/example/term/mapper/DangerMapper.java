package com.example.term.mapper;

import com.example.term.entity.DangerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


@Repository
public interface DangerMapper extends BaseMapper<DangerEntity> {

//    @Select("select * from danger ${ew.customSqlSegment}")
//    List<DangerEntity> findAll(@Param(Constants.WRAPPER) Wrapper wrapper);

}
