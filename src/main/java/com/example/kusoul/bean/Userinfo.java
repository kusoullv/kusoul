package com.example.kusoul.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Userinfo对象", description="")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("USERID")
    private String userid;

    @TableField("PWD")
    private String pwd;

    @TableField("USERNAME")
    private String username;

    @TableField("ORDERID")
    private String orderid;

    @TableField("PHONE")
    private Long phone;

    @TableField("ADDRESS")
    private String address;

    @TableField("IDCARD")
    private Long idcard;

    @TableField("GENDER")
    private String gender;

    @TableField("WECHATID")
    private String wechatid;

    @TableField("EMAIL")
    private String email;

    @TableField("CREATEDATE")
    private Date createdate;

    @TableField("CREATEID")
    private String createid;

    @TableField("UPDATEDATE")
    private Date updatedate;

    @TableField("UPDATEDATEID")
    private String updatedateid;

    @TableField("ROWID")
    private String rowid;


}
