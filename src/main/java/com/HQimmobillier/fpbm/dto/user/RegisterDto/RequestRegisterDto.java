package com.HQimmobillier.fpbm.dto.user.RegisterDto;

import lombok.Data;

@Data
public class RequestRegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
}
