package me.zeeeeeeek.backend.controllers.auth;

import lombok.NonNull;

public record AuthenticationResponse(@NonNull String jwtToken) {
}
