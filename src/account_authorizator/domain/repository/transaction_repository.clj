(ns account-authorizator.domain.repository.transaction_repository)

(def transactions (atom []))

(defn is-empty []
    (empty? @transactions))

(defn save [transaction]
    (swap! transactions conj transaction))

(defn get-transactions [] @transactions)

(defn clear []
    (reset! transactions []))
