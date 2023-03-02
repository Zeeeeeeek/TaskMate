package me.zeeeeeeek.backend.repositories;

import lombok.NonNull;
import me.zeeeeeeek.backend.models.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByUsername(@NonNull String username);

    Optional<User> findByEmail(@NonNull String email);
}
