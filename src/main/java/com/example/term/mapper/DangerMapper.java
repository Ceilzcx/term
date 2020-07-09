package com.example.term.mapper;

import com.example.term.entity.DangerEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DangerMapper extends BaseMapper<DangerEntity> {

    @Select("select * from danger where uid in (select uid from enterprise where id = #{eid})")
    List<DangerEntity> getDangerByEid(int eid);

    @Select("select * from danger where id in " +
            "(select did from rectification where id in " +
            "(select rid from acceptance where id = #{aid}) )")
    DangerEntity getDangerByAid(int aid);

}
