package com.example.board.domain;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Criteria {
    private int pageNum;
    private int amount; // 한 페이지에서 글 갯수 몇 개나 볼 건지 정할 수 있도록

    //for search
    private String type; // title => 'T', content => 'C', writer -> W // 경우의 수:7
    private String keyword;


    // 기본 생성자
    public Criteria(){
        this(1); // 생성자 내에서 새로운 생성자를 또 만들기 위해서 // this함수 <-라고 하는듯

    }

    // 커스텀생성자
    public Criteria(int pageNum){
        this.pageNum = pageNum; // 자신의 필드를 나타내기 위함
        this.amount = 2; //
        
    }

    // 커스텀이 된다
    public Criteria(int pageNum, String type, String keyword){
        this.pageNum = pageNum;
        this.type = type;
        this.keyword = keyword;
        this.amount = 2;
    }


    public String[] getTypeArr(){
        System.out.println("############");

        //TCW => [T, C, W] // 배열로 만들어줌
        String arr[]=(type == null ? new String[]{} : type.split(""));
        System.out.println("arr:" + Arrays.toString(arr));
        return arr;
    }

}
