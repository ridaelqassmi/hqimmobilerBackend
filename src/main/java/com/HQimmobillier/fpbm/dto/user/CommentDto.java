package com.HQimmobillier.fpbm.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private long Postid;
    private String message;
}
