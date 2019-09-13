(ns account-authorizator.domain.repository.account_repository
    (:require [account-authorizator.domain.entity.account_entity :refer :all]))

(def accounts (atom []))

(defn empty []
    (empty? @accounts))

(defn save [account]
    (swap! accounts conj account))

(defn get [] @accounts)
