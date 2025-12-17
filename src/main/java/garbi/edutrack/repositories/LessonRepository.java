package garbi.edutrack.repositories;

import garbi.edutrack.entities.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {
}