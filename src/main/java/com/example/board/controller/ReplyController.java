package com.example.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.domain.ReplyVO;
import com.example.board.service.ReplyService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/replies/*")
public class ReplyController {
    
    private final ReplyService service;

    public ReplyController(ReplyService service){
        this.service = service;
    }


    @PostMapping("/new/{bno}")
    public ResponseEntity<String> create(@PathVariable("bno") Long bno, @RequestBody ReplyVO vo) {
        vo.setBno(bno);
        int insertCount = service.newReply(vo);

        return insertCount == 1 
            ?new ResponseEntity<String>("success", HttpStatus.OK)
            :new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/list/{bno}")
    public ResponseEntity<List<ReplyVO>> getList(@PathVariable Long bno) {
        return new ResponseEntity<List<ReplyVO>>(service.getList(bno), HttpStatus.OK);
    }

    @GetMapping("/get/{rno}")
    public ResponseEntity<ReplyVO> get(@PathVariable Long rno) {
        return new ResponseEntity<ReplyVO>(service.get(rno), HttpStatus.OK);
    }
    
    @PutMapping("/modify/{rno}")
    public ResponseEntity<String> modify(@PathVariable Long rno, @RequestBody ReplyVO vo) {
        vo.setRno(rno);
        
        return service.modify(vo) == 1
            ? new ResponseEntity<String>("success", HttpStatus.OK)
            : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @DeleteMapping("/delete/{rno}")
    public ResponseEntity<String> remove(@PathVariable Long rno){
        return service.delete(rno) == 1
            ? new ResponseEntity<String>("success", HttpStatus.OK)
            : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
