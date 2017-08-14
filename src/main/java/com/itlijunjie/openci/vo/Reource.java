package com.itlijunjie.openci.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ljj on 13/12/2016.
 * 用户资源表
 */
@Entity
@Table(name="t_reource")
public class Reource {
    /**
     * 资源唯一标识，数据库主键
     */
    private int id;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 资源名称
     */
    private String name;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotEmpty(message="用户名不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
