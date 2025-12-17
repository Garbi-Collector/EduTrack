package garbi.edutrack.repositories;

import garbi.edutrack.entities.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleEntity, UUID> {
}