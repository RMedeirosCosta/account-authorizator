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

(defn initialize [active-card, available-limit]
    (if (is-empty)
        (get (save (create (get-accounts) active-card available-limit)) 0)
        (create (get-accounts) active-card available-limit)))
