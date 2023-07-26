package fun.sssdnsy.web.controller.monitor;

import cn.hutool.core.collection.CollUtil;
import fun.sssdnsy.constant.CacheConstants;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.domain.SysCache;
import fun.sssdnsy.utils.JsonUtils;
import fun.sssdnsy.utils.StringUtils;
import fun.sssdnsy.utils.redis.CacheUtils;
import fun.sssdnsy.utils.redis.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 缓存监控
 *
 * @author sssdnsy
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/cache")
public class CacheController {

    private final RedissonConnectionFactory connectionFactory;

    private final static List<SysCache> CACHES = new ArrayList<>();


    {
        CACHES.add(new SysCache(CacheConstants.LOGIN_TOKEN_KEY, "用户信息"));
        CACHES.add(new SysCache(CacheConstants.SYS_CONFIG_KEY, "配置信息"));
        CACHES.add(new SysCache(CacheConstants.SYS_DICT_KEY, "数据字典"));
        CACHES.add(new SysCache(CacheConstants.CAPTCHA_CODE_KEY, "验证码"));
        CACHES.add(new SysCache(CacheConstants.REPEAT_SUBMIT_KEY, "防重提交"));
        CACHES.add(new SysCache(CacheConstants.RATE_LIMIT_KEY, "限流处理"));
        CACHES.add(new SysCache(CacheConstants.PWD_ERR_CNT_KEY, "密码错误次数"));
    }

    /**
     * 获取缓存监控列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:cache:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception {
        RedisConnection connection   = connectionFactory.getConnection();
        Properties      info         = connection.info();
        Properties      commandStats = connection.info("commandstats");
        Long            dbSize       = connection.dbSize();

        Map<String, Object> result = new HashMap<>(3);
        result.put("info", info);
        result.put("dbSize", dbSize);

        List<Map<String, String>> pieList = new ArrayList<>();
        commandStats.stringPropertyNames().forEach(key -> {
            Map<String, String> data     = new HashMap<>(2);
            String              property = commandStats.getProperty(key);
            data.put("name", StringUtils.removeStart(key, "cmdstat_"));
            data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
            pieList.add(data);
        });
        result.put("commandStats", pieList);
        return AjaxResult.success(result);
    }

    /**
     * 获取缓存监控列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:cache:list')")
    @GetMapping("/getNames")
    public AjaxResult cache() {
        return AjaxResult.success(CACHES);
    }

    /**
     * 获取缓存监控缓存名列表
     */
    @PreAuthorize("@ss.hasPermi('monitor:cache:list')")
    @GetMapping("/getKeys/{cacheName}")
    public AjaxResult getCacheKeys(@PathVariable String cacheName) {
        Collection<String> cacheKeys = new HashSet<>(0);
        if (isCacheNames(cacheName)) {
            Set<Object> keys = CacheUtils.keys(cacheName);
            if (CollUtil.isNotEmpty(keys)) {
                cacheKeys = keys.stream().map(Object::toString).collect(Collectors.toList());
            }
        } else {
            cacheKeys = RedisUtils.keys(cacheName + "*");
        }
        return AjaxResult.success(cacheKeys);
    }

    /**
     * 获取缓存监控缓存值详情
     *
     * @param cacheName 缓存名
     * @param cacheKey  缓存key
     */
    @PreAuthorize("@ss.hasPermi('monitor:cache:list')")
    @GetMapping("/getValue/{cacheName}/{cacheKey}")
    public AjaxResult getCacheValue(@PathVariable String cacheName, @PathVariable String cacheKey) {
        Object cacheValue;
        if (isCacheNames(cacheName)) {
            cacheValue = CacheUtils.get(cacheName, cacheKey);
        } else {
            cacheValue = RedisUtils.getCacheObject(cacheKey);
        }
        SysCache sysCache = new SysCache(cacheName, cacheKey, JsonUtils.toJsonString(cacheValue));
        return AjaxResult.success(sysCache);
    }

    /**
     * 清理缓存监控缓存名
     *
     * @param cacheName 缓存名
     */
    @PreAuthorize("@ss.hasPermi('monitor:cache:list')")
    @DeleteMapping("/clearCacheName/{cacheName}")
    public AjaxResult clearCacheName(@PathVariable String cacheName) {
        if (isCacheNames(cacheName)) {
            CacheUtils.clear(cacheName);
        } else {
            RedisUtils.deleteKeys(cacheName + "*");
        }
        return AjaxResult.success();
    }

    /**
     * 清理缓存监控Key
     *
     * @param cacheKey key名
     */
    @PreAuthorize("@ss.hasPermi('monitor:cache:list')")
    @DeleteMapping("/clearCacheKey/{cacheKey}")
    public AjaxResult clearCacheKey(@PathVariable String cacheName, @PathVariable String cacheKey) {
        if (isCacheNames(cacheName)) {
            CacheUtils.evict(cacheName, cacheKey);
        } else {
            RedisUtils.deleteObject(cacheKey);
        }
        return AjaxResult.success();
    }

    /**
     * 清理全部缓存监控
     */
    @PreAuthorize("@ss.hasPermi('monitor:cache:list')")
    @DeleteMapping("/clearCacheAll")
    public AjaxResult clearCacheAll() {
        RedisUtils.deleteKeys("*");
        return AjaxResult.success();
    }

    private boolean isCacheNames(String cacheName) {
        return !StringUtils.contains(cacheName, ":");
    }

}
