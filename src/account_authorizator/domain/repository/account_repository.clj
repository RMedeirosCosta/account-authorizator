(ns account-authorizator.domain.repository.account_repository)

(def accounts (atom []))

(defn is-empty []
    (empty? @accounts))

(defn save [account]
    (swap! accounts conj account))

(defn get-accounts [] @accounts)

(defn clear []
    (reset! accounts []))
