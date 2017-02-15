package empmgmt.repository;

import empmgmt.model.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

	Optional<Workplace> findByNameIgnoreCase(String name);
}
