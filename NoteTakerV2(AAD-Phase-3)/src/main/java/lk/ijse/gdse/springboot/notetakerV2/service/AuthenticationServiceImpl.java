package lk.ijse.gdse.springboot.notetakerV2.service;

import lk.ijse.gdse.springboot.notetakerV2.dao.UserDao;
import lk.ijse.gdse.springboot.notetakerV2.dto.Impl.UserDto;
import lk.ijse.gdse.springboot.notetakerV2.jwtModels.JWTAuthResponse;
import lk.ijse.gdse.springboot.notetakerV2.jwtModels.SignIn;
import lk.ijse.gdse.springboot.notetakerV2.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private final UserDao userDao;
    private final JWTService jwtService;
    private final Mapping mapping;
    //utils
    private final AuthenticationManager authenticationManager;

    @Override
    public JWTAuthResponse signIn(SignIn signIn) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));
        var userByEmail = userDao.findByEmail(signIn.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
        var generatedToken = jwtService.generateToken(userByEmail);
        return JWTAuthResponse.builder().token(generatedToken).build();
    }

    @Override
    public JWTAuthResponse signUp(UserDto signUpUser) {
        var savedUser = userDao.save(mapping.convertToUserEntity(signUpUser));
        var genToken = jwtService.generateToken(savedUser);
        return JWTAuthResponse.builder().token(genToken).build();
    }

    @Override
    public JWTAuthResponse refreshToken(String accessToken) {
        var username = jwtService.extractUsername(accessToken);
        var userEntity =
                userDao.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        var refreshToken = jwtService.refreshToken(userEntity);
        return JWTAuthResponse.builder().token(refreshToken).build();
    }
}
