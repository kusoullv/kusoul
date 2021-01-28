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
@ApiModel(value="Address", description="")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @TableField("adresssID")
    private String adresssID;

    private String flg;

    @TableField("phoneNumber")
    private Long phoneNumber;

    @TableField("createDate")
    private Date createDate;

    @TableField("createID")
    private String createID;

    @TableField("updateDate")
    private Date updateDate;

    @TableField("updateID")
    private String updateID;


}
