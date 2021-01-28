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
@ApiModel(value="Order对象", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @TableField("goodID")
    private String goodID;

    @TableField("goodTilte")
    private String goodTilte;

    @TableField("orderDate")
    private Date orderDate;

    @TableField("goodDes")
    private String goodDes;


}
