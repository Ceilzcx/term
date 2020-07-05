package com.example.term.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("users")
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private String type;

    private Integer eid;

    @Override
    public String toString() {
        return "Users{" +
        "id=" + id +
        ", username=" + username +
        ", nickname=" + nickname +
        ", password=" + password +
        ", type=" + type +
        ", eid=" + eid +
        "}";
    }
}
