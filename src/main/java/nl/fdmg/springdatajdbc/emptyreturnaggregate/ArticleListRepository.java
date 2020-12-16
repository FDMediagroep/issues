package nl.fdmg.springdatajdbc.emptyreturnaggregate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleListRepository extends CrudRepository<ArticleList, Long> {

    Optional<ArticleList> findByPublicationAndName(@Param("publication") String publication, @Param("name") String name);

}
