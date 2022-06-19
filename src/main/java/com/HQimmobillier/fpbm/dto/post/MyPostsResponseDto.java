package com.HQimmobillier.fpbm.dto.post;

import lombok.Data;

import java.util.Date;

@Data
public class MyPostsResponseDto {
    private long id;
    private Date date;
    private boolean State;
    private String type;
    private String title;


}
