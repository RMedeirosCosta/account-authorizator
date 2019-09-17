(ns account-authorizator.domain.service.transaction_service
    (:require 
             [account-authorizator.domain.service.account_service :refer [create]]))

(defn make-transaction [account, transaction]
    (create true (- (:availableLimit account) (:amount transaction))))