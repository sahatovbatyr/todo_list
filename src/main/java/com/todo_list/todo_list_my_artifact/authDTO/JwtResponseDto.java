package com.todo_list.todo_list_my_artifact.authDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDto {

    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;

}
