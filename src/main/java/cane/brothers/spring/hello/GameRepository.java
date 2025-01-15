package cane.brothers.spring.hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
interface GameRepository extends CrudRepository<Game, UUID> {

    List<Game> findByChat(@Param("chat") Chat chat);

}
