package com.example.term.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("enterprise")
@Data
public class EnterpriseEntity implements Serializable {

    private static final long serialVersionUID = 8475782736490280446L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String creditCode;

    private String representative;

    private String telephone;

    private String address;

    private String position;

    @Override
    public String toString() {
        return "Enterprise{" +
        "id=" + id +
        ", name=" + name +
        ", creditCode=" + creditCode +
        ", representative=" + representative +
        ", telephone=" + telephone +
        ", address=" + address +
        ", position=" + position +
        "}";
    }
}
