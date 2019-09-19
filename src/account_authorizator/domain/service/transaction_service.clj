(ns account-authorizator.domain.service.transaction_service
    (:require 
             [account-authorizator.domain.service.account_service :refer [get-all, create]]
             [account-authorizator.domain.entity.account_entity :refer [->Account]]
             [account-authorizator.domain.repository.account_repository :as acc]
             [account-authorizator.domain.repository.transaction_repository :as trs]
             [account-authorizator.domain.entity.transaction_entity :refer [->Transaction, is-high-frequency-small-interval, same-sorted-transactions, happened-in-two-minutes]]))

(defn remaining-limit [availableLimit, amount]
    (- availableLimit amount))

(defn build-account-with-error [account, error]
    (->Account (:activeCard account) (:availableLimit account) [error]))

(defn update-db [remaining-limit, transaction]
    (acc/clear)
    (trs/save transaction)
    (last (acc/save (create true remaining-limit))))

(defn make-transaction-with-actived-card [account, transaction]
    (let [remaining-limit
        (remaining-limit (:availableLimit account) (:amount transaction))]
        (if (>= remaining-limit 0)
            (update-db remaining-limit transaction)
            (build-account-with-error account "insufficient-limit"))))

(defn is-double-transaction [past-transactions, transaction]
    (let [sorted-transactions (same-sorted-transactions past-transactions transaction)]
    (if (not-empty sorted-transactions)
        (happened-in-two-minutes (last sorted-transactions) transaction))))

(defn make-transaction
    ([account, transaction] (if (not (:activeCard account))
                                    (build-account-with-error account "card-not-active")
                                    (make-transaction-with-actived-card account transaction)))
    ([past-transactions, account, transaction] (cond
                                                    (is-double-transaction past-transactions transaction) (build-account-with-error account "double-transaction")
                                                    (is-high-frequency-small-interval past-transactions transaction) (build-account-with-error account "high-frequency-small-interval")
                                                    :else (make-transaction account transaction))))

(defn complete-transaction [merchant, amount, time]
   (if (empty? (get-all))
       (->Account false 0 ["account-not-found"])
       (make-transaction (trs/get-transactions)
                          (last (acc/get-accounts))
                          (->Transaction merchant amount time))))