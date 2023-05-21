package com.muqi.backendsl.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {

//    @TableId(type = IdType.AUTO)
    private int id;

    private String avatarURL;

    private String userName;


}
