package com.huuphuoc.api.redis.repository;

import com.huuphuoc.api.redis.model.TokenBlacklist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RedisRepository extends CrudRepository<TokenBlacklist, String> {
}
