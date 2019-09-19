(ns account-authorizator.domain.repository.account_repository_test
    (:require 
            [clojure.test :refer [is, deftest, use-fixtures]]
            [account-authorizator.helper.account_helper :refer [get-expected-account, clear-database]]
            [account-authorizator.domain.repository.account_repository :refer [is-empty, save, get-accounts]]))

(defn get-expected-created-accounts []
    [(get-expected-account)])

(deftest is-empty-must-return-true-when-there-is-no-account
  (is (= true (is-empty))))

(deftest is-empty-must-return-false-after-an-insertion
  (save "xpto")  
  (is (= false
         (is-empty))))

(deftest get-accounts-must-return-created-accounts
  (save (get-expected-account))
  (is (= (get-expected-created-accounts)
         (get-accounts))))

(use-fixtures :each clear-database)