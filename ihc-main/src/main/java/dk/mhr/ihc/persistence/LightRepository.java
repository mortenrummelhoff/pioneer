package dk.mhr.ihc.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mortenrummelhoff on 16/03/2017.
 */
@Repository
public interface LightRepository extends JpaRepository<LightEvent, Long> {



}
