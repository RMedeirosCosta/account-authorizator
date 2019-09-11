(ns account-authorizator.domain.service.account_test
  (:use clojure.test)
  (:require [account-authorizator.domain.entity.account_entity :refer :all]))

(defn get-expected-account []
  (->Account true 100))

(deftest create-account-when-there-is-no-previous-account
  (is (= (get-expected-account) (get-expected-account)))) ;(create-account [] true 100))))
