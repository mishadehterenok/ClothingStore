package com.mishadehterenok.project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class LoginAccountDto {
    private String login;
    private String password;
}
