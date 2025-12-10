package com.example.board.service;

import java.util.List;

import com.example.board.domain.BoardVO;

// method of CRUD
public interface BoardService {


    // get all data
    public List<BoardVO> getList();

    public List<BoardVO> getList(int pageNum, String type, String keyword);

    // just one read
    public BoardVO get(Long bno);

    // write new contant
    public int register(BoardVO board);

    // update
    public boolean modify(BoardVO board);

    //delete
    public boolean remove(Long bno);
}
