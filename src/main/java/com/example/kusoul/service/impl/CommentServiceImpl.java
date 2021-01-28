package com.example.kusoul.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kusoul.domain.Comment;
import com.example.kusoul.service.ICommentService;
import com.maqh.demo.dao.ss.CommentMapper;
import com.maqh.demo.domain.ss.Comment;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public IPage<Comment> findListByPage(Integer page, Integer pageCount){
        IPage<Comment> wherePage = new Page<>(page, pageCount);
        Comment where = new Comment();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Comment comment){
        return baseMapper.insert(comment);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Comment comment){
        return baseMapper.updateById(comment);
    }

    @Override
    public Comment findById(Long id){
        return  baseMapper.selectById(id);
    }
}
