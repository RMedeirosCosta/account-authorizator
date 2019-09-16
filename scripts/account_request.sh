create-account-request() {
  echo "curl -sX POST http://localhost:3000/accounts --data '$1' --header 'Content-type:application/json'" 
  echo
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

  echo "Second request..."
  log-and-send-request "$(create-account-request "$(get-json-with-resource-prefix 'account_request_2.json')")"
}

main
