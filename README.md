This a very simple wrapper to use for the [blob build api](https://blob.build/docs).

### Usage
Its as simple as initalizing a new client and just accessing the methods.
```
BlobClient client = BlobAPI.newClient(TOKEN);
```
**Note that TOKEN can be null when calling endpoints that require no authorization. (eg. projects list)**

The methods are pretty self explanitory. (I will add javadocs)
They all are ran asynchronously using the [HttpClient](https://docs.oracle.com/en%2Fjava%2Fjavase%2F11%2Fdocs%2Fapi%2F%2F/java.net.http/java/net/http/HttpClient.html) and return [CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html).
```
client.getProjects().join(); // blocks thread
client.getProjects().whenComplete((response, ex) -> {}); // does not block
```

If you need help ask on the discord: https://discord.gg/RJsVvVd
