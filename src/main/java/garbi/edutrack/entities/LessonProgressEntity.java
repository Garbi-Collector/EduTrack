package garbi.edutrack.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Representa el progreso de un usuario en una lección específica.
 *
 * Registra si una lección fue completada, cuándo ocurrió dicha finalización y
 * cuánto tiempo dedicó el usuario a la misma. Esta información permite calcular
 * métricas como el porcentaje de avance en un curso y el tiempo total de estudio.
 *
 * Esta entidad es fundamental para la generación de estadísticas, reportes y
 * consultas complejas a nivel de base de datos.
 */

@Entity
@Table(
        name = "lesson_progress",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "lesson_id"})
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private LessonEntity lesson;

    @Column(nullable = false)
    private boolean completed;

    private LocalDateTime completedAt;

    @Column(name = "time_spent_minutes", nullable = false)
    private int timeSpentMinutes;

    @PrePersist
    protected void onCreate() {
        this.completed = false;
        this.timeSpentMinutes = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        if (this.completed && this.completedAt == null) {
            this.completedAt = LocalDateTime.now();
        }
    }
}
