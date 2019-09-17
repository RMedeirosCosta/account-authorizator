(ns account-authorizator.domain.service.transaction_service
    (:require 
             [account-authorizator.domain.service.account_service :refer [create]]
             [account-authorizator.domain.entity.account_entity :refer [->Account]]))

(defn remaining-limit [availableLimit, amount]
    (- availableLimit amount))

(defn build-account-with-error [account, error]
    (->Account (:activeCard account) (:availableLimit account) [error]))

(defn make-transaction-with-actived-card [account, transaction]
    (let [remaining-limit
        (remaining-limit (:availableLimit account) (:amount transaction))]
        (if (> remaining-limit 0)
            (create true remaining-limit)
            (build-account-with-error account "insufficient-limit"))))

(defn make-transaction
    ([account, transaction] (if (not (:activeCard account))
                                    (build-account-with-error account "card-not-active")
                                    (make-transaction-with-actived-card account transaction)))
    ([past-transactions, account, transaction] (if (< (count past-transactions) 1)
                                                   (make-transaction account transaction))))