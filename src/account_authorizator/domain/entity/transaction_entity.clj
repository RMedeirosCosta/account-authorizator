(ns account-authorizator.domain.entity.transaction_entity)

(require '[clj-time.core :as t])

(defrecord Transaction [merchant, amount, time])

(defn equals [transaction, anotherTransaction]
    (and (= (:merchant transaction) (:merchant anotherTransaction))
         (= (:amount transaction) (:amount anotherTransaction))))

(defn happened-in-two-minutes [transaction, anotherTransaction]
    (<= (t/in-minutes (t/interval (:time transaction) (:time anotherTransaction)) )
        2))