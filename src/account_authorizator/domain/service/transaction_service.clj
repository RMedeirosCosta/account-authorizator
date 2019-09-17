(ns account-authorizator.domain.service.transaction_service
    (:require 
             [account-authorizator.domain.service.account_service :refer [create]]
             [account-authorizator.domain.entity.account_entity :refer [->Account]]))

(defn remaining-limit [availableLimit, amount]
    (- availableLimit amount))

(defn make-transaction [account, transaction]
    (let [remaining-limit
          (remaining-limit (:availableLimit account) (:amount transaction))]
    (if (> remaining-limit 0)
        (create true remaining-limit)
        (->Account (:activeCard account) (:availableLimit account) ["insufficient-limit"]))))