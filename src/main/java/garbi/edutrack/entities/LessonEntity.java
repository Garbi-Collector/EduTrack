package garbi.edutrack.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa una lección dentro de un módulo.
 *
 * La lección es la unidad mínima de contenido educativo y puede contener texto,
 * recursos o material multimedia. Cada lección pertenece a un módulo y tiene un
 * orden específico dentro del mismo.
 *
 * El progreso del usuario se registra a nivel de lección, permitiendo cálculos
 * detallados de avance y métricas de aprendizaje.
 */
@Entity
@Table(
        name = "lessons",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"module_id", "position"})
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false)
    private ModuleEntity module;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "duration_minutes", nullable = false)
    private int durationMinutes;

    @Column(nullable = false)
    private int position;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
