


Provide API with your endpoint

```
for example:  zodzie.com/outgoing-webhook
```

Setup a reverse proxy

```
install HAproxy 

create an ACL --> 
if url contains "/outgoing-webhook"
send connection to java-apifw 

```

Run java-apifw on App Server

```
compile & jar code 
install java
java -jar java-apifw.jar
```


## Authors

* **Ryan Rosado** - *Initial work* - [java-apifw]


