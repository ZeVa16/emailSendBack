package com.example.sendEmail.dtos.userDtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String name;
    private String email;
    private Integer points;

}
