(ns account-authorizator.domain.service.account_service
    (:require [account-authorizator.domain.entity.account_entity :refer [->Account]]))

(defn create
   ([created-accounts active-card available-limit] (if
                                                       (empty? created-accounts) 
                                                       (create active-card available-limit)
                                                       (->Account active-card available-limit ["account-already-initialized"])))
   ([active-card, available-limit] (->Account active-card available-limit [])))

(def created-accounts (atom []))

(defn initialize-account [active-card, available-limit]
    (if (empty? @created-accounts)
        (swap! created-accounts conj (create @created-accounts active-card available-limit))
        (create @created-accounts active-card available-limit)))
