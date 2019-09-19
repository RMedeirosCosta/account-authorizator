(ns account-authorizator.domain.repository.transaction_repository_test
    (:require 
            [clojure.test :refer [is, deftest, use-fixtures]]
            [account-authorizator.helper.account_helper :refer [clear-database]]
            [account-authorizator.domain.entity.transaction_entity :refer [->Transaction]]
            [account-authorizator.domain.repository.transaction_repository :refer [is-empty, save, get-transactions]]))

(require '[clj-time.core :as t])

(defn get-expected-transaction []
    (->Transaction "Itachi Uchiha" 666 (t/now)))

(defn get-expected-persisted-transactions []
    [(get-expected-transaction)])

(deftest is-empty-must-return-true-when-there-is-no-transaction
  (is (= true (is-empty))))

(deftest is-empty-must-return-false-after-an-insertion
   (save "xpto")
   (is (= false
          (is-empty))))

(deftest get-transactions-must-return-persisted-transactions
  (save (get-expected-transaction))
  (is (= (get-expected-persisted-transactions)
         (get-transactions))))

(use-fixtures :each clear-database)