package com.example.frame.domain.po;

import com.example.frame.domain.enums.UserSexEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPO {

    private Long id;
    private String userName;
    private String passWord;
    private UserSexEnum userSex;
    private Boolean valid;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
}
