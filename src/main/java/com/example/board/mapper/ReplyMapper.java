package com.example.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.board.domain.ReplyVO;

@Mapper
public interface ReplyMapper {
    public int newReply(ReplyVO vo);
    public List<ReplyVO> getList(Long bno);
    public ReplyVO get(Long rno);
    public int modify(ReplyVO vo);
    public int delete(Long rno);
}
