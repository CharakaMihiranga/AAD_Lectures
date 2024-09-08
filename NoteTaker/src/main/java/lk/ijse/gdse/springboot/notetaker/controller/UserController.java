package lk.ijse.gdse.springboot.notetaker.controller;

import lk.ijse.gdse.springboot.notetaker.dto.UserDto;
import lk.ijse.gdse.springboot.notetaker.service.UserService;
import lk.ijse.gdse.springboot.notetaker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
   @Autowired
   private final UserService userService;
   //Save user
   @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //http://localhost:8080/notetaker/api/v1/users
    public ResponseEntity<String> saveUser(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") String profilePic
    ) {
       //Handle profile pic
       String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic); //Base64 encoding used to convert the image to a string
       //build the user object
       UserDto buildUserDto = new UserDto();
       buildUserDto.setFirstName(firstName);
       buildUserDto.setLastName(lastName);
       buildUserDto.setEmail(email);
       buildUserDto.setPassword(password);
       buildUserDto.setProfilePic(base64ProfilePic);
       //send to the service layer
       return new ResponseEntity<>(userService.saveUser(buildUserDto), HttpStatus.CREATED);
    }

}

// ** MultiPart Form data **//
// MultiPart Form data is a way to send data to the server in the form of a form. It is used to send files and text data to the server.
//It send data as parts. Each part is a key-value pair. The key is the name of the input field and the value is the value of the input field.

// ** Maximum file size & Maximum request size ** //
//the difference between  maxFileSize and maxRequestSize is that maxFileSize is the maximum size of a single file that can be uploaded,
// while maxRequestSize is the maximum size of the entire request, including all files and other form data.