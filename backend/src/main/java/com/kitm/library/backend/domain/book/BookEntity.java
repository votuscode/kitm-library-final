package com.kitm.library.backend.domain.book;

import com.kitm.library.backend.domain.author.AuthorEntity;
import com.kitm.library.backend.domain.category.CategoryEntity;
import com.kitm.library.backend.domain.order.OrderEntity;
import com.kitm.library.backend.domain.wish.WishEntity;

import javax.persistence.*;
import java.util.UUID;
import lombok.*;

/**
 * @author votuscode (https://github.com/votuscode)
 * @version 1.0
 * @since 10.04.22
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookEntity {

  @Id
  @GeneratedValue()
  @Column(updatable = false, nullable = false, length = 16)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column()
  private String description;

  @Column()
  private Integer pages;

  @Column(length = 32)
  private String isbn;

  @Lob
  @Column
  private String image;

  @Column(name = "author_id", nullable = false, insertable = false, updatable = false)
  private UUID authorId;

  @Column(name = "category_id", nullable = false, insertable = false, updatable = false)
  private UUID categoryId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false)
  private AuthorEntity authorEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private CategoryEntity categoryEntity;

  @OneToOne(mappedBy = "bookEntity", fetch = FetchType.LAZY)
  private OrderEntity orderEntity;

  @OneToOne(mappedBy = "bookEntity", fetch = FetchType.LAZY)
  private WishEntity wishEntity;
}
