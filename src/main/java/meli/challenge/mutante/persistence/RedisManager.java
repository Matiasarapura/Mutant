package meli.challenge.mutante.persistence;

import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Log4j2

//singleton to manage redis conections
public class RedisManager {

    private static RedisManager INSTANCE = null;
    private JedisPool jedisPool;
    private JedisPoolConfig poolConfig;




    private RedisManager(String host,Integer port) {

        log.info("redis_url: " + host + ":" + port);



        poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(64);
        jedisPool = new JedisPool(poolConfig, host, port, 10 * 1000);

        Jedis jedis = jedisPool.getResource();
        jedis.close();
    }

    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    public synchronized static void init(String host,Integer port) {
        INSTANCE = new RedisManager(host,port);

    }
    public synchronized static RedisManager getInstance() {

        return INSTANCE;
    }

    public void saveByKey(String key, String value) {
        var jedis = getJedis();
        jedis.set(key, value);
        jedis.close();
    }

    public String getByKey(String key) {
        var jedis = getJedis();
        var h = jedis.get(key);

        jedis.close();
        return h;
    }

    public void counter(String key) {

        var jedis = getJedis();
        if(jedis.get(key) != null) {
            jedis.incr(key);
        }else {
            saveByKey(key,"0");
        }
        jedis.close();

    }

}






















