package nl.fdmg.springdatajdbc.emptyreturnaggregate;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("article_list_entry")
public class ArticleListEntry {

    @Column("article_id")
    private final long articleId;

}
