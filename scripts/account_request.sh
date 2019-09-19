base-request() {
  echo "curl -sX "$1" http://localhost:3000/$2 --header 'Content-type:application/json'"
}

accounts-base-request() {
  echo "$(base-request $1 accounts)"
}

transactions-base-request() {
  echo "$(base-request $1 transactions)"
}

create-account-request() {
  echo "$(accounts-base-request 'POST') --data '$1'"
}

create-transaction-request() {
  echo "$(transactions-base-request 'POST') --data '$1'"
}

get-accounts-request() {
  echo "$(accounts-base-request 'GET')"
}

get-transactions-request() {
  echo "$(transactions-base-request 'GET')"
}

get-json-with-resource-prefix() {
  echo "$(cat ../resource/$1)"
}

log-and-send-request() {
  echo "$1"
  eval $1
  echo
  echo
}

main() {
  echo "First request..."
  log-and-send-request "$(create-account-request "$(get-json-with-resource-prefix 'account_request.json')")"

  #echo "Second request..."
  #log-and-send-request "$(create-account-request "$(get-json-with-resource-prefix 'account_request_2.json')")"

  echo "Third request..."
  log-and-send-request "$(get-accounts-request)"

  #log-and-send-request "$(create-transaction-request "$(get-json-with-resource-prefix 'transaction_request.json')")"
  #log-and-send-request "$(create-transaction-request "$(get-json-with-resource-prefix 'transaction_request_2.json')")"
  log-and-send-request "$(create-transaction-request "$(get-json-with-resource-prefix 'transaction_request_3.json')")"
  log-and-send-request "$(create-transaction-request "$(get-json-with-resource-prefix 'transaction_request_4.json')")"
  log-and-send-request "$(get-transactions-request)"
}

main
