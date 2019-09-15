(ns account-authorizator.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :refer [site]]
            [ring.middleware.json :refer [wrap-json-body, wrap-json-response]]
            [compojure.route :as route]
            [account-authorizator.domain.service.account_service :refer [create]]
            [account-authorizator.domain.entity.account_entity :refer [to-string]]))

(defroutes app-routes
  (POST "/account" request
      (let [account (get-in request [:body :account])]
        {:status 200
         :body {:account account}
        }))
      (route/resources "/")
      (route/not-found "Not Found"))


(def app
  (-> (site app-routes)
      (wrap-json-body {:keywords? true})
       wrap-json-response))
