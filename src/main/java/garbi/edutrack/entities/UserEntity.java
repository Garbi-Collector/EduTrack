package garbi.edutrack.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa tanto alumnos como creadores de cursos
 */
@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
