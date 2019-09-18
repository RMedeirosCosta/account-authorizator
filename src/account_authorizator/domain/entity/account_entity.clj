(ns account-authorizator.domain.entity.account_entity)

(defrecord Account [activeCard, availableLimit, violations])

(defn get-violations [violations]
    (if (not-empty violations) 
        (clojure.string/join ["\"", (get violations 0), "\""])
        ""))

(defn to-string [account]
    (clojure.string/join 
        ["{ \"account\": { \"activeCard\": ", (:activeCard account), ", ",
                          "\"availableLimit\": ", (:availableLimit account), " }"
         ", \"violations\": [", (get-violations (:violations account)), "] }"]))