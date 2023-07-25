package com.gamerduck.commons.general;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExpiringMap<K, V> {
    private final Map<K, Long> expirationTimes = new ConcurrentHashMap<>();
    private final Map<K, V> map = new ConcurrentHashMap<>();
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public ExpiringMap(long expirationSeconds, Plugin plugin) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            long now = System.currentTimeMillis();
            for (K key : map.keySet()) {
                if (now - expirationTimes.get(key) > TimeUnit.SECONDS.toMillis(expirationSeconds)) {
                    map.remove(key);
                    expirationTimes.remove(key);
                }
            }
        }, 0, expirationSeconds * 20);
    }

    public void put(K key, V value) {
        map.put(key, value);
        expirationTimes.put(key, System.currentTimeMillis());
    }

    public V get(K key) {
        return map.get(key);
    }

    public void remove(K key) {
        map.remove(key);
        expirationTimes.remove(key);
    }
}