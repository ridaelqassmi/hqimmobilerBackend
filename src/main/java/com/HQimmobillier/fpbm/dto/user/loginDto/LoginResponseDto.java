package com.HQimmobillier.fpbm.dto.user.loginDto;

import com.HQimmobillier.fpbm.entity.User;
import lombok.Data;

@Data
public class LoginResponseDto {
    String token;
    User user;
}
