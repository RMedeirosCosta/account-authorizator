(ns account-authorizator.domain.service.account_service
    (:require
            [account-authorizator.domain.entity.account_entity :refer [->Account]]
            [account-authorizator.domain.repository.account_repository :refer [is-empty, save, get-accounts]]))

(defn create
   ([created-accounts active-card available-limit] (if
                                                       (empty? created-accounts) 
                                                       (create active-card available-limit)
                                                       (->Account active-card available-limit ["account-already-initialized"])))
   ([active-card, available-limit] (->Account active-card available-limit [])))

(defn get-first [accounts]
    (get accounts 0))

(defn initialize [active-card, available-limit]
    (if (is-empty)
        (get-first (save (create (get-accounts) active-card available-limit)))
        (create (get-accounts) (:activeCard (get-first (get-accounts))) (:availableLimit (get-first (get-accounts))))))

(defn get-all []
    (get (get-accounts) 0))
