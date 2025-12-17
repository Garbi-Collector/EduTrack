package garbi.edutrack.repositories;

import garbi.edutrack.entities.LessonProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonProgressRepository extends JpaRepository<LessonProgressEntity, UUID> {
}