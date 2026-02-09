package com.huuphuoc.api.redis.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("_redishash")
@Builder
public class TokenBlacklist {

    @Id
    private String jwtID;

    @TimeToLive(unit = TimeUnit.MINUTES)
    private  Long expiredTime;


}
