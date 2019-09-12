(ns account-authorizator.domain.entity.account_entity_test
    (:use clojure.test)
    (:require [account-authorizator.domain.entity.account_entity :refer :all]))

(defn get-expected-json-string [] 
    "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 }, \"violations\": [ \"account-already-initialized\" ] }")

(deftest must-parse-account-to-json-string
  (is (= (get-expected-json-string) 
         (to-string (->Account true 100 ["account-already-initialized"])))))
