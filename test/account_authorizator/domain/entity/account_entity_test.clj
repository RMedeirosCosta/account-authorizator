(ns account-authorizator.domain.entity.account_entity_test
    (:require [clojure.test :refer [deftest, is]]
              [account-authorizator.domain.entity.account_entity :refer [->Account, to-string]]))

(defn build-json-string
  [string] (clojure.string/join ["{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 }, \"violations\": [", (if (clojure.string/blank? string) 
                                                                                                                           ""
                                                                                                                           string), "] }"]))

(defn get-expected-json-string 
    ([violation] (build-json-string violation))
    ([] (get-expected-json-string "")))

(deftest must-parse-account-to-json-string
  (is (= (get-expected-json-string "\"account-already-initialized\"") 
         (to-string (->Account true 100 ["account-already-initialized"])))))

(deftest must-parse-account-to-json-string-when-there-is-no-violations
  (is (= (get-expected-json-string) 
         (to-string (->Account true 100 [])))))