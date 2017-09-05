package pl.edu.uksw.RunningEvents.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import pl.edu.uksw.RunningEvents.model.entity.Race;

import java.util.List;

/**
 * Created by Nogaz on 10.08.2017.
 */
@Component
public interface RaceJpaRepository extends JpaRepository<Race, Long> {
    Race findById(Long id);
    List<Race> findByName(String name);
    List<Race> findByDistanceLessThan(int max);
}
