(ns account-authorizator.domain.service.transaction_service_test
    (:require 
             [clojure.test :refer [deftest, is]]
             [account-authorizator.domain.entity.account_entity :refer [->Account]]
             [account-authorizator.domain.entity.transaction_entity :refer [->Transaction]]
             [account-authorizator.domain.service.transaction_service :refer [make-transaction]]))

(defn get-expected-account []
    (->Account true 20 []))

(deftest must-make-a-transaction
    (is (= (get-expected-account)
           (make-transaction 
                (->Account true 100 []) 
                (->Transaction "name" 80 (java.util.Date.))))))
  