package me.zeeeeeeek.backend.repositories;

import me.zeeeeeeek.backend.models.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
