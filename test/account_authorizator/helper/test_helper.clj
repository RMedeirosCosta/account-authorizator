(ns account-authorizator.helper.test_helper
    (:use account-authorizator.domain.entity.account_entity))

(defn get-expected-account []
  (->Account true 100 []))