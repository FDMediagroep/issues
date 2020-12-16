package nl.fdmg.springdatajdbc.emptyreturnaggregate;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Table("article_list_meta")
public class ArticleList {

    @Id
    private final Long id;

    private final String publication;

    private final String name;

    @MappedCollection(idColumn = "list_id", keyColumn = "seq")
    @AccessType(AccessType.Type.FIELD)
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    List<ArticleListEntry> entries = new ArrayList<>();

    @Version
    private long version;

    private Integer max;

    @PersistenceConstructor
    public ArticleList(Long id, String publication, String name) {
        this.id = id;
        this.publication = publication;
        this.name = name;
    }

    public static ArticleList of(String publication, String name) {
        return new ArticleList(null, publication, name);
    }

    public void setArticles(List<Long> articles) {
        entries = articles.stream()
                .map(ArticleListEntry::new)
                .collect(Collectors.toList());
    }

    public List<Long> getArticles() {
        return entries
                .stream()
                .map(ArticleListEntry::getArticleId)
                .collect(Collectors.toList());
    }

}
