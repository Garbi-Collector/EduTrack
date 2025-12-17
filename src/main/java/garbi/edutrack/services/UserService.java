package garbi.edutrack.services;

import garbi.edutrack.entities.UserEntity;
import garbi.edutrack.entities.enums.RoleEntity;

import java.util.UUID;

public interface UserService {

    UserEntity createUser(
            String name,
            String email,
            String rawPassword,
            RoleEntity role
    );

    UserEntity changeUserName(UUID userId, String newName);
}
