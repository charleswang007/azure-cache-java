package example.demo;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

/**
 * Redis test
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        boolean useSsl = true;
        String cacheHostname = System.getenv("REDISCACHEHOSTNAME");
        String cachekey = System.getenv("REDISCACHEKEY");

        // Connect to the Azure Cache for Redis over the TLS/SSL port using the key.
        // JedisShardInfo shardInfo = new JedisShardInfo(cacheHostname, 6380, useSsl);
        // shardInfo.setPassword(cachekey); /* Use your access key. */
        // Jedis jedis = new Jedis(shardInfo);

        // Cluster Setting
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(500);
        config.setMinIdle(2);
        config.setMaxIdle(500);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort(cacheHostname,13000));
        jedisClusterNodes.add(new HostAndPort(cacheHostname, 13001));
        JedisCluster jedis = new JedisCluster(jedisClusterNodes, 10000, 10000, 100, cachekey, config);

        // Perform cache operations using the cache connection object...

        // Print Redis Info
        System.out.println( "Cache Name : " + cacheHostname);
        System.out.println( "Cache Key : " + cachekey);

        // Test Clustering
        System.out.println( "\nCache Command  : GET shard0key" );
        System.out.println( "Cache Response : " + jedis.get("shard0key"));

        System.out.println( "\nCache Command  : GET shard1key" );
        System.out.println( "Cache Response : " + jedis.get("shard1key"));

        // Simple PING command        
        System.out.println( "\nCache Command  : Ping" );
        System.out.println( "Cache Response : " + jedis.ping());

        // Simple get and put of integral data types into the cache
        System.out.println( "\nCache Command  : GET Message" );
        System.out.println( "Cache Response : " + jedis.get("Message"));

        System.out.println( "\nCache Command  : SET Message" );
        System.out.println( "Cache Response : " + jedis.set("Message", "Hello! The cache is working from Java!"));

        // Demonstrate "SET Message" executed as expected...
        System.out.println( "\nCache Command  : GET Message" );
        System.out.println( "Cache Response : " + jedis.get("Message"));
		
		System.out.println( "\nCache Command  : SET Message" );
        System.out.println( "Cache Response : " + jedis.set("friend", "Puppy"));

        // Demonstrate "SET Message" executed as expected...
        System.out.println( "\nCache Command  : GET Message" );
        System.out.println( "Cache Response : " + jedis.get("friend"));

        // Get the client list, useful to see if connection list is growing...
        System.out.println( "\nCache Command  : CLIENT LIST" );
        System.out.println( "Cache Response : " + jedis.clientList());

        jedis.close();
    }
}