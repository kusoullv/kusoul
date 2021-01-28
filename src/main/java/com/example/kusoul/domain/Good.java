package com.example.kusoul.domain;

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
@ApiModel(value="Good对象", description="")
public class Good implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String description;

    @TableField("typeID")
    private String typeID;

    private String price;

    @TableField("totlieNumber")
    private Integer totlieNumber;

    private String status;

    @TableField("createID")
    private String createID;

    @TableField("createDate")
    private Date createDate;

    @TableField("updateID")
    private String updateID;

    @TableField("updateDate")
    private Date updateDate;


}
