package me.zeeeeeeek.backend.services.auth;

import lombok.RequiredArgsConstructor;
import me.zeeeeeeek.backend.models.auth.RefreshToken;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.repositories.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(User user) {
        return new RefreshToken(
                generateRandomStringUntilIsUnique(),
                getExpiryDate(),
                user
        );
    }

    public void saveRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken createAndSaveRefreshToken(User user) {
        RefreshToken refreshToken = createRefreshToken(user);
        saveRefreshToken(refreshToken);
        return refreshToken;
    }

    public boolean isValidToken(String token) {
        return refreshTokenRepository.findById(token)
                .map(refreshToken -> refreshToken.getExpiryDate().isAfter(LocalDateTime.now()))
                .orElse(false);
    }

    public User getUserByToken(String token) throws NoSuchElementException {
        return refreshTokenRepository.findById(token)
                .orElseThrow()
                .getUser();
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteById(token);
    }

    private String generateRandomStringUntilIsUnique() {
        String token = generateRandomString();
        while(refreshTokenRepository.existsById(token))
            token = generateRandomString();
        return token;
    }

    private String generateRandomString() {
        byte[] bytes = new byte[64];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    private LocalDateTime getExpiryDate() {
        return LocalDateTime.now().plusMonths(1);
    }
}
