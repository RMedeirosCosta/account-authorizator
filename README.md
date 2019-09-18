# account-authorizator

FIXME

## Usage

### Run the application locally

`lein ring server` or
`docker build -t account-authorizator . && docker run -it -p 3000:3000 --rm --name account-authorizator account-authorizator`

### Run the tests

`lein test`

### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

### Packaging as war

`lein ring uberwar`

## License

Copyright ©  FIXME
