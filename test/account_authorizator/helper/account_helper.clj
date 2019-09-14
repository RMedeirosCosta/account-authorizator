(ns account-authorizator.helper.account_helper
    (:require [account-authorizator.domain.entity.account_entity :refer [->Account]]))

(defn get-expected-account []
  (->Account true 100 []))