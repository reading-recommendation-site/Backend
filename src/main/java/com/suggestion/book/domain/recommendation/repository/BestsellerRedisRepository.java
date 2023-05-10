package com.suggestion.book.domain.recommendation.repository;

import com.suggestion.book.domain.recommendation.entity.Bestseller;
import org.springframework.data.repository.CrudRepository;

public interface BestsellerRedisRepository extends CrudRepository<Bestseller, String> {
}
