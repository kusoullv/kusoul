package com.example.kusoul.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kusoul.bean.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
