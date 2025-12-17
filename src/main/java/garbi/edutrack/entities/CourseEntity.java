package garbi.edutrack.entities;

import garbi.edutrack.entities.enums.StatusesEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa un curso dentro de la plataforma educativa.
 *
 * Un curso agrupa módulos y lecciones organizadas de forma jerárquica y es creado
 * por un usuario con rol de instructor. Los usuarios pueden inscribirse en el
 * curso para acceder a su contenido y realizar el seguimiento de su progreso.
 *
 * El estado del curso permite controlar su disponibilidad (borrador, publicado,
 * archivado).
 */

@Entity
@Table(name = "courses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusesEntity status;

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
