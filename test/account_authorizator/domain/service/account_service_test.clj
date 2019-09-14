(ns account-authorizator.domain.service.account_service_test
  (:require [clojure.test :refer [deftest, is]]
            [account-authorizator.domain.entity.account_entity :refer [->Account]]
            [account-authorizator.helper.account_helper :refer [get-expected-account]]
            [account-authorizator.domain.service.account_service :refer [create]]))

(defn get-expected-already-initialized-account []
  (->Account true 100 ["account-already-initialized"]))

(deftest create-account-when-there-is-no-previous-account
  (is (= (get-expected-account) (create [] true 100))))

(deftest create-account-when-there-is-previous-account
  (is (= (get-expected-already-initialized-account) 
         (create [(get-expected-account)] true 100))))

; FIXMEE not working
; (deftest initialize-account-must-keep-account-state
;   (is (= (get-expected-already-initialized-account)
;          ((initialize-account true 100)
;           (initialize-account true 100)))))