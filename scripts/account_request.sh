create-account-request() {
  echo "curl -sX POST http://localhost:3000/account --data '$1' --header 'Content-type:application/json'" 
}

get-json-with-resource-prefix() {
  echo "$(cat ../resource/$1)"
}

main() {
  echo "First request..."
  local REQUEST=$(create-account-request "$(get-json-with-resource-prefix 'account_request.json')")
  echo $REQUEST
  eval $REQUEST
  echo 
}

main
