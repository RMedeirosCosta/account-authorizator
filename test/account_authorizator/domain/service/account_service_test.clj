(ns account-authorizator.domain.service.account_service_test
  (:require [clojure.test :refer [deftest, is, use-fixtures]]
            [account-authorizator.domain.entity.account_entity :refer [->Account]]
            [account-authorizator.helper.account_helper :refer [get-expected-account, clear-database]]
            [account-authorizator.domain.service.account_service :refer [create, initialize, get-all]]))

(defn get-expected-already-initialized-account []
  (->Account true 100 ["account-already-initialized"]))

(deftest create-account-when-there-is-no-previous-account
  (is (= (get-expected-account) (create [] true 100))))

(deftest create-account-when-there-is-previous-account
  (is (= (get-expected-already-initialized-account) 
         (create [(get-expected-account)] true 100))))

(deftest initialize-must-keep-account-state
   (initialize true 100)
   (is (= (get-expected-already-initialized-account)
          (initialize true 100))))
          
(deftest initialize-when-there-is-no-previous-account
    (is (= (get-expected-account)
           (initialize true 100))))

(deftest get-all-must-return-only-the-first-one
    (initialize true 100)
    (is (= (get-expected-account)
           (get-all))))

(use-fixtures :each clear-database)