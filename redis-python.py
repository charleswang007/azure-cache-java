import redis

myHostname = ""
myPassword = ""

r = redis.StrictRedis(host=myHostname, port=6380, password=myPassword, ssl=True)
#r = redis.RedisCluster(host=myHostname, port=15000, password=myPassword, ssl=True)

result = r.ping()
print("Ping returned : " + str(result))

for i in range(100):
    key = "test"+str(i)
    val = i+10000
    r.set(key,val)
    print("set " + key + " to " + str(val))

result = r.set("Message", "Hello!, The cache is working with Python!")
print("SET Message returned : " + str(result))

result = r.client_list()
print("CLIENT LIST returned : ")
for c in result:
    print("id : " + c['id'] + ", addr : " + c['addr'])