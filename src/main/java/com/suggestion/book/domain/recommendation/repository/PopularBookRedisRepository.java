package com.suggestion.book.domain.recommendation.repository;

import com.suggestion.book.domain.recommendation.entity.PopularBook;
import org.springframework.data.repository.CrudRepository;

public interface PopularBookRedisRepository extends CrudRepository<PopularBook,String> {
}
