(ns account-authorizator.domain.service.account_service_test
  (:use clojure.test)
  (:require [account-authorizator.domain.entity.account_entity :refer :all]
            [account-authorizator.domain.service.account_service :refer :all]))

(defn get-expected-account []
  (->Account true 100 []))

(defn get-expected-already-initialized-account []
  (->Account true 100 ["account-already-initialized"]))

(deftest create-account-when-there-is-no-previous-account
  (is (= (get-expected-account) (create-account [] true 100))))

(deftest create-account-when-there-is-previous-account
  (is (= (get-expected-already-initialized-account) 
         (create-account [(get-expected-account)] true 100))))

; FIXMEE not working
; (deftest initialize-account-must-keep-account-state
;   (is (= (get-expected-already-initialized-account)
;          ((initialize-account true 100)
;           (initialize-account true 100)))))