package garbi.edutrack.entities;

import garbi.edutrack.entities.enums.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa la inscripción de un usuario a un curso.
 *
 * Esta entidad modela la relación muchos a muchos entre usuarios y cursos,
 * registrando cuándo un usuario se inscribe y el estado de dicha inscripción
 * (activa, completada o abandonada).
 *
 * Es una pieza clave para el control de acceso al contenido y para el cálculo
 * del progreso general del usuario dentro de un curso.
 */

@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "course_id"})
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Column(nullable = false, updatable = false)
    private LocalDateTime enrolledAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;

    @PrePersist
    protected void onEnroll() {
        this.enrolledAt = LocalDateTime.now();
        this.status = EnrollmentStatus.ACTIVE;
    }
}
