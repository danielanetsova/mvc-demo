package bg.softuni.MVCDemo.repositories;

import bg.softuni.MVCDemo.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findFirstByName(String name);
    List<Project> findAllByIsFinishedTrue();
}
