package garbi.edutrack.services.impl;

import garbi.edutrack.entities.UserEntity;
import garbi.edutrack.entities.enums.RoleEntity;
import garbi.edutrack.repositories.UserRepository;
import garbi.edutrack.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity createUser(String name,
                                 String email,
                                 String rawPassword,
                                 RoleEntity role) {

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }

        UserEntity user = UserEntity.builder()
                .name(name)
                .email(email)
                // 🔐 HASH de la contraseña
                .password(passwordEncoder.encode(rawPassword))
                .role(role)
                .build();

        return userRepository.save(user);
    }

    @Override
    public UserEntity changeUserName(UUID userId, String newName) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setName(newName);

        // no hace falta save explícito por @Transactional
        return user;
    }
}
