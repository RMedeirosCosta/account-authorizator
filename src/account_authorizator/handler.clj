(ns account-authorizator.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :refer [site]]
            [ring.middleware.json :refer [wrap-json-body, wrap-json-response]]
            [compojure.route :as route]
            [account-authorizator.domain.service.account_service :refer [initialize, get-all]]
            [account-authorizator.domain.service.transaction_service :refer [complete-transaction, get-all-transactions]]
            [account-authorizator.domain.entity.account_entity :refer [to-string]]))

(require '[clj-time.format :as f])
            
(defn get-iso-date [date]
  (f/parse (f/formatters :date-time) date))          

(extend-protocol cheshire.generate/JSONable
  org.joda.time.DateTime
    (to-json [dt gen]
      (cheshire.generate/write-string gen (str dt))))  

(defroutes app-routes
  (POST "/accounts" request
      (let [account (get-in request [:body :account])]
        {:status 200
         :body (to-string (initialize (:activeCard account) (:availableLimit account)))}))
  (POST "/transactions" request
      (let [transaction (get-in request [:body :transaction])]
        {:status 200
         :body (to-string (complete-transaction 
                                               (:merchant transaction)
                                               (:amount transaction)
                                               (get-iso-date (:time transaction))))}))

  (GET "/transactions" request
        {:status 200
         :body (get-all-transactions)})                                    
  (GET "/accounts" request
        {:status 200
         :body (to-string (get-all))})
  (route/resources "/")
  (route/not-found "Not Found"))


(def app
  (-> (site app-routes)
      (wrap-json-body {:keywords? true})
       wrap-json-response))
