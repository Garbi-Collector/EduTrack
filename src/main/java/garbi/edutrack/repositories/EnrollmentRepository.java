package garbi.edutrack.repositories;

import garbi.edutrack.entities.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, UUID> {
}