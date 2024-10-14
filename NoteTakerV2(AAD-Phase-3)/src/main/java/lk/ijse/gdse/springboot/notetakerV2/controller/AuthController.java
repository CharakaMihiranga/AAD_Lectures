package lk.ijse.gdse.springboot.notetakerV2.controller;

import lk.ijse.gdse.springboot.notetakerV2.dto.Impl.UserDto;
import lk.ijse.gdse.springboot.notetakerV2.exception.DataPersistFailedException;
import lk.ijse.gdse.springboot.notetakerV2.jwtModels.JWTResponse;
import lk.ijse.gdse.springboot.notetakerV2.jwtModels.SignIn;
import lk.ijse.gdse.springboot.notetakerV2.service.UserService;
import lk.ijse.gdse.springboot.notetakerV2.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth") //http://localhost:8080/notetaker/api/v1/auth
public class AuthController {

    @Autowired
    private final UserService userService;

    @PostMapping(value = "signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JWTResponse> signUp(
            @RequestPart("firstName") String firstName,
            @RequestPart("lastName") String lastName,
            @RequestPart("email") String email,
            @RequestPart("password") String password,
            @RequestPart("profilePic") MultipartFile profilePic
    ){
       try{
           String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic); //Base64 encoding used to convert the image to a  string
           UserDto buildUserDto = new UserDto();
           buildUserDto.setFirstName(firstName);
           buildUserDto.setLastName(lastName);
           buildUserDto.setEmail(email);
           buildUserDto.setPassword(password);
           buildUserDto.setProfilePic(base64ProfilePic);
           //send to the service layer
           userService.saveUser(buildUserDto);
           return new ResponseEntity<>(HttpStatus.CREATED);
       } catch (DataPersistFailedException e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @PostMapping(value = "signin")
    public ResponseEntity<JWTResponse> signIn(@RequestBody SignIn signIn){
        return null;
    }

}
