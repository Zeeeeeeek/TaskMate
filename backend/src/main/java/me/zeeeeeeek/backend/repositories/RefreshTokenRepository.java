package me.zeeeeeeek.backend.repositories;

import me.zeeeeeeek.backend.models.auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
