package com.gfs.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleUserData {
    private String email;
    private String name;
    private String picture;
    private String token;
}
