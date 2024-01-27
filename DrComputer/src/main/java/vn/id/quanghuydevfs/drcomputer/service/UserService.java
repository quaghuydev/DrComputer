package vn.id.quanghuydevfs.drcomputer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.id.quanghuydevfs.drcomputer.controller.auth.AuthenticationResponse;
import vn.id.quanghuydevfs.drcomputer.dto.user.UserDto;
import vn.id.quanghuydevfs.drcomputer.model.token.Token;
import vn.id.quanghuydevfs.drcomputer.model.token.TokenRepository;
import vn.id.quanghuydevfs.drcomputer.model.token.TokenType;
import vn.id.quanghuydevfs.drcomputer.model.user.User;
import vn.id.quanghuydevfs.drcomputer.repository.UserRepository;
import vn.id.quanghuydevfs.drcomputer.security.jwt.JwtService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public List<User> getUsers() {
        return repository.findAll();
    }
    @Transactional
    public boolean deleteUserById(Long id) {
        var user = repository.findById(id);

        if (user.isPresent()) {
            tokenRepository.deleteTokenByUser_Id(id);
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public AuthenticationResponse updateUserById(Long id, UserDto u){
        var user = repository.findById(id).orElse(null);
        if (user!=null){
            user.setId(id);
            user.setFullname(u.getFullname());
            user.setEmail(u.getEmail());
            user.setPhoneNumber(u.getPhoneNumber());
            user.setRoles(u.getRole());
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        return null;
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
