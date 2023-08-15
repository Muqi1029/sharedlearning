package com.muqi.backendsl.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest implements Serializable {

    private String loginAccount;

    private String loginPassword;

    private static final long serialVersionUID = 1L;
}
