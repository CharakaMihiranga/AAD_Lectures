package lk.ijse.gdse.springboot.notetaker.controller;

import lk.ijse.gdse.springboot.notetaker.dto.UserDto;
import lk.ijse.gdse.springboot.notetaker.service.UserService;
import lk.ijse.gdse.springboot.notetaker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
       String base64ProfilePic = AppUtil.toBase64ProfilePic(profilePic); //Base64 encoding used to convert the image to a  string
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

   //Delete user
   @DeleteMapping("/{userId}") //http://localhost:8080/notetaker/api/v1/users/{userId}
   public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId){
        return userService.deleteUser(userId)
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

   @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE) //http://localhost:8080/notetaker/api/v1/users/{userId}
   public UserDto getSelectedUser(@PathVariable("userId") String userId){
       return userService.getSelectedUser(userId);
   }

   @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) //http://localhost:8080/notetaker/api/v1/users
   public List<UserDto> getAllUsers(){
       return userService.getAllUsers();
   }

   @PatchMapping(value = "/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //http://localhost:8080/notetaker/api/v1/users/{userId}
   public ResponseEntity<String> updateUser(
           @PathVariable ("userId") String userId,
           @RequestPart("firstName") String updateFirstName,
           @RequestPart("lastName") String updateLastName,
           @RequestPart("email") String updateEmail,
           @RequestPart("password") String updatePassword,
           @RequestPart("profilePic") String updateProfilePic
               ){
       //Handle profile pic
       String updatedBase64ProfilePic = AppUtil.toBase64ProfilePic(updateProfilePic); //Base64 encoding used to convert the image to a  string
       //build the user object
       UserDto updatedUserDto = new UserDto();
       updatedUserDto.setUserId(userId);
       updatedUserDto.setFirstName(updateFirstName);
       updatedUserDto.setLastName(updateLastName);
       updatedUserDto.setEmail(updateEmail);
       updatedUserDto.setPassword(updatePassword);
       updatedUserDto.setProfilePic(updatedBase64ProfilePic);
       return userService.updateUser(updatedUserDto)
               ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

}

// ** MultiPart Form data **//
// MultiPart Form data is a way to send data to the server in the form of a form. It is used to send files and text data to the server.
//It send data as parts. Each part is a key-value pair. The key is the name of the input field and the value is the value of the input field.

// ** Maximum file size & Maximum request size ** //
//the difference between  maxFileSize and maxRequestSize is that maxFileSize is the maximum size of a single file that can be uploaded,
// while maxRequestSize is the maximum size of the entire request, including all files and other form data.

//** Restful API **//
//RESTful API is an architectural style for an application program interface (API) that uses HTTP requests to perform four operations:
//GET (read), POST (create), PUT (update), and DELETE (delete). RESTful API is used to create web services.
//difference between rest and restfull is that rest is an architectural style and restfull is an implementation of that style.
//Restful API is a type of web service that is built to work with HTTP requests.
// It is a stateless architecture that allows clients to access and manipulate web resources using a uniform and predefined set of stateless operations.