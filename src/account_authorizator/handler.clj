(ns account-authorizator.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [compojure.route :as route]
            [account-authorizator.domain.service.account_service :refer [create]]
            [account-authorizator.domain.entity.account_entity :refer [to-string]]))

    (defroutes app-routes
      (POST "/" request
        (let [name (get-in request [:body :name])]                       
          {:status 200
           :body {:name name
           :desc (str "The name you sent to me was " name)}}))
      (route/resources "/")
      (route/not-found "Not Found"))


   (def app
    (-> (handler/site app-routes)
        (middleware/wrap-json-body {:keywords? true})
        middleware/wrap-json-response))
