package com.yoongi.springweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String id;
    private String password;
}
