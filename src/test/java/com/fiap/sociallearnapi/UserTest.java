package com.fiap.sociallearnapi;

import static org.junit.jupiter.api.Assertions.*;

import com.fiap.sociallearn.SociallearnApplication;
import com.fiap.sociallearn.controller.UserController;
import com.fiap.sociallearn.model.Gender;
import com.fiap.sociallearn.request.UserRequest;
import com.fiap.sociallearn.response.UserResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SociallearnApplication.class)
public class UserTest {
    @Autowired
    private UserController userController;

    @Autowired
	PasswordEncoder encoder;

    @Test
    public void register_user(){
        assertNotNull(userController);

        UserRequest userRequest = new UserRequest("jhon","jhon","jhon@snow.com", encoder.encode("jjj"), Gender.MALE);
        ResponseEntity<UserResponse> response = userController.register(userRequest);

        assertEquals(200, response.getStatusCodeValue());
    }
}
