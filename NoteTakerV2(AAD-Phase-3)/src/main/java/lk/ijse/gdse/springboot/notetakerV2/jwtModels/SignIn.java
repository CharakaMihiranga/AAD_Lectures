package lk.ijse.gdse.springboot.notetakerV2.jwtModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder // this is used to create the object using the builder pattern => builder pattern is used to
// create an object with a large number of parameters in a more readable way
public class SignIn {
    private String email;
    private String password;
}
