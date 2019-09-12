(ns account-authorizator.domain.entity.account_entity)

(defrecord Account [active-card, available-limit, violations])

(defn to-string [account]
    (clojure.string/join 
        ["{ \"account\": { \"activeCard\": ", (:active-card account),
         ", \"availableLimit\": ", (:available-limit account), " }"
         ", \"violations\": [ \"", (get (:violations account) 0), "\" ] }"]))
