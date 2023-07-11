package com.project.shared_calender.common.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.data.redis.cache.RedisCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ReturnTypeCacheResolver extends SimpleCacheResolver {

    @Setter

    private ObjectMapper objectMapper;

    public ReturnTypeCacheResolver() {
        super();
    }

    @Override
    public Collection<? extends Cache> resolveCaches(final CacheOperationInvocationContext<?> context) {
        Collection<String> cacheNames = getCacheNames(context);
        if (cacheNames == null) {
            return Collections.emptyList();
        }
        Collection<Cache> result = new ArrayList<>(cacheNames.size());
        for (String cacheName : cacheNames) {
            Cache cache = getCacheManager().getCache(cacheName);

            // WrappingRedisCache에 returnType 셋팅
            cache = wrappingRedisCache(context, cache);

            if (cache == null) {
                throw new IllegalArgumentException();
            }
            result.add(cache);
        }
        return result;
    }

    private Cache wrappingRedisCache(final CacheOperationInvocationContext<?> context, final Cache cache) {
        if (cache instanceof RedisCache) {
            return new WrappingRedisCache((RedisCache) cache,
                    context.getMethod().getReturnType(),
                    objectMapper);
        }
        return cache;
    }

}
