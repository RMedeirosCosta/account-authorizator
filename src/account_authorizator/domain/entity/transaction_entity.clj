(ns account-authorizator.domain.entity.transaction_entity)

(defrecord Transaction [merchant, amount, time])

(defn equals [transaction, anotherTransaction]
    (and (= (:merchant transaction) (:merchant anotherTransaction))
         (= (:amount transaction) (:amount anotherTransaction))))

(defn happened-in-two-minutes [transaction, anotherTransaction]
    (< (- (.getMinutes (:time transaction))
          (.getMinutes (:time anotherTransaction))
       2)))