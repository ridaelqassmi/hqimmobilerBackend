package com.HQimmobillier.fpbm.dto;

import lombok.Data;

@Data
public class MessageDto {
    private Long postId;
    private Long sendTo;
    private String MessageContent;
}
