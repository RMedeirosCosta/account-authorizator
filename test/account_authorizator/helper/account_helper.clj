(ns account-authorizator.helper.account_helper
    (:require
              [account-authorizator.domain.repository.account_repository :as acc]
              [account-authorizator.domain.repository.transaction_repository :as trs]
              [account-authorizator.domain.entity.account_entity :refer [->Account]]))

(defn get-expected-account []
  (->Account true 100 []))

(defn clear-database [f]
  (acc/clear)
  (trs/clear)
  (f))