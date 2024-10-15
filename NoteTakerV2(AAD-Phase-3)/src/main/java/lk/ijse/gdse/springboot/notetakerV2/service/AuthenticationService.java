package lk.ijse.gdse.springboot.notetakerV2.service;

import lk.ijse.gdse.springboot.notetakerV2.dto.Impl.UserDto;
import lk.ijse.gdse.springboot.notetakerV2.jwtModels.JWTAuthResponse;
import lk.ijse.gdse.springboot.notetakerV2.jwtModels.SignIn;

public interface AuthenticationService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDto signUp);
    JWTAuthResponse refreshToken(String accessToken);
}
