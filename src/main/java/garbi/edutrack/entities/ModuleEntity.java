package garbi.edutrack.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa un módulo dentro de un curso.
 *
 * Un módulo funciona como una unidad organizativa que agrupa un conjunto de
 * lecciones relacionadas. Cada módulo pertenece a un único curso y define el
 * orden en el que sus lecciones deben ser presentadas.
 *
 * Esta entidad permite estructurar el contenido del curso de forma clara y
 * escalable.
 */

@Entity
@Table(name = "modules",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"course_id", "position"})
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int position;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
