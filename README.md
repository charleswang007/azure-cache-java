# azure-cache-java

### Command

1. from PowerShell
```
cd redistest
$env:REDISCACHEHOSTNAME="charlesredis1.redis.cache.windows.net"
$env:REDISCACHEKEY="xxxxxx"
mvn compile
mvn exec:java -D exec.mainClass=example.demo.App
```

2. from Command Prompt
```
cd redistest
set REDISCACHEHOSTNAME=charlesredis1.redis.cache.windows.net
set REDISCACHEKEY=xxxxxx
mvn compile
mvn exec:java -D exec.mainClass=example.demo.App
```

### Reference

- https://docs.microsoft.com/en-us/azure/azure-cache-for-redis/cache-java-get-started
- https://docs.microsoft.com/en-us/java/api/overview/azure/rediscache?view=azure-java-stable