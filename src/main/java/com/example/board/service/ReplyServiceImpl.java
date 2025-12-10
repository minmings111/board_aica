package com.example.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.domain.ReplyVO;
import com.example.board.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{

    private final ReplyMapper mapper;

    public ReplyServiceImpl(ReplyMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public int newReply(ReplyVO vo) {
        return mapper.newReply(vo);
    }

    @Override
    public List<ReplyVO> getList(Long bno) {
        return mapper.getList(bno);
    }

    @Override
    public ReplyVO get(Long rno) {
        return mapper.get(rno);
    }

    @Override
    public int modify(ReplyVO vo) {
        return mapper.modify(vo);
    }

    @Override
    public int delete(Long rno) {
        return mapper.delete(rno);
    }
    
    
    
}
