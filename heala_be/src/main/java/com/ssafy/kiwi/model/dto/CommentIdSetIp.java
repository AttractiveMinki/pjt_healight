package com.ssafy.kiwi.model.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentIdSetIp {
    private List<Integer> commentIdSet;
    private int userId;
}
