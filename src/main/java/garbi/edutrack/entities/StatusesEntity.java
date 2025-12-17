package garbi.edutrack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statuses")
public class StatusesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "status", unique = true, nullable = false)
    private String status;

}
