package dev.codesquad.java.baseball08.dao.crud;

import dev.codesquad.java.baseball08.entity.Inning;
import org.springframework.data.repository.CrudRepository;

public interface InningRepository extends CrudRepository<Inning, Long> {
}
