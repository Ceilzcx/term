package com.example.term.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@TableName("acceptance")
@Data
public class AcceptanceEntity implements Serializable {
    private static final long serialVersionUID = -4372049426558614224L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "accept_option")
    private String acceptOption;
    @TableField
    private Integer uid;
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    private LocalDateTime createDate;
    @TableField(value = "accept_status")
    private String acceptStatus;
    @TableField
    private Integer rid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptanceEntity that = (AcceptanceEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(acceptOption, that.acceptOption) &&
                Objects.equals(uid, that.uid) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(acceptStatus, that.acceptStatus) &&
                Objects.equals(rid, that.rid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, acceptOption, uid, createDate, acceptStatus, rid);
    }
}
