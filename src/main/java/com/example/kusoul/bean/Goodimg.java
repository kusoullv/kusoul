package com.example.kusoul.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Blob;

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
@ApiModel(value="Goodimg对象", description="")
public class Goodimg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @TableField("goodImg")
    private Blob goodImg;


}
