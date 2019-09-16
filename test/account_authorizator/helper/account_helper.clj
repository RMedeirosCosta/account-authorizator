(ns account-authorizator.helper.account_helper
    (:require
              [account-authorizator.domain.repository.account_repository :refer [clear]]
              [account-authorizator.domain.entity.account_entity :refer [->Account]]))

(defn get-expected-account []
  (->Account true 100 []))

(defn clear-database [f]
  (clear)
  (f))