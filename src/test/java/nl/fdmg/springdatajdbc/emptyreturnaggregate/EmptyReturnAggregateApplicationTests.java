package nl.fdmg.springdatajdbc.emptyreturnaggregate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Import(FlywayConfig.class)
@SpringBootTest
class EmptyReturnAggregateApplicationTests {

    @Autowired
    ArticleListRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnFullAggregate(){
        // given
        ArticleList articleList = ArticleList.of("tst", "test");
        articleList.setArticles(Arrays.asList(1L, 2L, 3L));

        // when
        ArticleList ret = repository.save(articleList);

        // then
        assertThat(ret, notNullValue());
        assertThat(ret.entries, equalTo(articleList.entries)); // fails

    }

}
