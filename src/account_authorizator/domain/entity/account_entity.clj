(ns account-authorizator.domain.entity.account_entity)

(defrecord Account [active-card, available-limit, violations])

(defn get-violations [violations]
    (if (not (empty? violations)) 
        (clojure.string/join ["\"", (get violations 0), "\""])
        ""))

(defn to-string [account]
    (clojure.string/join 
        ["{ \"account\": { \"activeCard\": ", (:active-card account), ", ",
                          "\"availableLimit\": ", (:available-limit account), " }"
         ", \"violations\": [", (get-violations (:violations account)), "] }"]))