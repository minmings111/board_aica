package com.example.board.service;

import java.util.List;

import com.example.board.domain.ReplyVO;

public interface ReplyService {
    public int newReply(ReplyVO vo);
    public List<ReplyVO> getList(Long bno);
    public ReplyVO get(Long rno);
    public int modify(ReplyVO vo);
    public int delete(Long rno);
}
