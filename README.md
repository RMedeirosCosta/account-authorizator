# Account Authorizator
It's an web API that authorizes a transaction for a specific account following a set of predefined rules.



### Prerequisites
- [Clojure](https://github.com/clojure/clojure)
- [Leiningen](https://github.com/technomancy/leiningen)
or
- [Docker](https://github.com/docker)


## Running the tests

```
lein test
```

It will run integration and unit tests.

## Deployment

```
lein ring server-headless
```

or

```
docker build -t account-authorizator . && docker run -it -p 3000:3000 --rm --name account-authorizator account-authorizator
```

## How to call the API
There ara just two endpoints `accounts` and `transactions`. You just call them and use the verbs `HTTP` verbs `POST` and `GET`.

#### Create an account
```
curl -sX POST http://localhost:3000/accounts --header 'Content-type:application/json' --data '{ "account": { "activeCard": true, "availableLimit": 9000 } }'

{ "account": { "activeCard": true, "availableLimit": 9000 }, "violations": [] }
```

#### Getting the account
```
curl -s http://localhost:3000/accounts
```

#### Complete a transaction 
```
curl -sX POST http://localhost:3000/transactions --header 'Content-type:application/json' --data '{ "transaction": { "merchant": "Chuck Berry", "amount": 20, "time": "2019-02-13T12:00:00.000Z" } }'

{ "account": { "activeCard": true, "availableLimit": 8980 }, "violations": [] }
```

#### Getting the transactions
```
curl -s http://localhost:3000/transactions
```


You can also can run the [account_request.sh](https://github.com/ricardomedeirosdacostajunior/account-authorizator/blob/master/scripts/account_request.sh) script. You just need to enter in the scripts directory and run it:

```
cd scripts/ && ./account_request.sh
```


<details>
<summary>
Output
</summary>

```
curl -sX POST http://localhost:3000/accounts --header 'Content-type:application/json' --data '{ "account": { "activeCard": true, "availableLimit": 9000 } }'
{ "account": { "activeCard": true, "availableLimit": 9000 }, "violations": [] }

curl -sX POST http://localhost:3000/accounts --header 'Content-type:application/json' --data '{ "account": { "activeCard": true, "availableLimit": 350 } }'
{ "account": { "activeCard": true, "availableLimit": 9000 }, "violations": ["account-already-initialized"] }

curl -sX GET http://localhost:3000/accounts --header 'Content-type:application/json'
{ "account": { "activeCard": true, "availableLimit": 9000 }, "violations": [] }

curl -sX POST http://localhost:3000/transactions --header 'Content-type:application/json' --data '{ "transaction": { "merchant": "Chuck Berry", "amount": 20, "time": "2019-02-13T11:00:00.000Z" } }'
{ "account": { "activeCard": true, "availableLimit": 8980 }, "violations": [] }

curl -sX POST http://localhost:3000/transactions --header 'Content-type:application/json' --data '{ "transaction": { "merchant": "Bill Haley", "amount": 640, "time": "2019-02-13T11:00:00.000Z" } }'
{ "account": { "activeCard": true, "availableLimit": 8340 }, "violations": [] }

curl -sX POST http://localhost:3000/transactions --header 'Content-type:application/json' --data '{ "transaction": { "merchant": "Elvis Presley", "amount": 666, "time": "2019-02-13T14:00:00.000Z" } }'
{ "account": { "activeCard": true, "availableLimit": 7674 }, "violations": [] }

curl -sX GET http://localhost:3000/transactions --header 'Content-type:application/json'
[{"merchant":"Chuck Berry","amount":20,"time":"2019-02-13T11:00:00.000Z"},{"merchant":"Bill Haley","amount":640,"time":"2019-02-13T11:00:00.000Z"},{"merchant":"Elvis Presley","amount":666,"time":"2019-02-13T14:00:00.000Z"}]

```
</details>





