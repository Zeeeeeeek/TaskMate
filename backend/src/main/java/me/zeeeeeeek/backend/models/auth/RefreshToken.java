package me.zeeeeeeek.backend.models.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.zeeeeeeek.backend.models.user.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @Column(unique = true)
    private String token;
    @Column
    private LocalDateTime expiryDate;

    @OneToOne
    private User user;
}
