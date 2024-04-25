package com.sbaldass.booksstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
        private Long id;
        private String username;
        private String email;
        private String password;
        private boolean isAdmin;


        public UserDTO(String mail, String number) {
                this.email = mail;
                this.password = number;
        }
}
