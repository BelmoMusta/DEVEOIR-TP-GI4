package com.ensa.gi4.appuser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    private String appUserRole;

    private Long id;
    private String userName;
    private String password;
    public AppUser(String userName,
                   String password,
                   String appUserRole) {
        this.userName = userName;
        this.password = password;

        this.appUserRole = appUserRole;
         }

}
