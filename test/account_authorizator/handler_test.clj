(ns account-authorizator.handler-test
  (:require [clojure.test :refer [deftest, is]]
            [account-authorizator.handler :refer [app]]
            [ring.mock.request :as mock]))

(defn get-account []
  "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 }, \"violations\": [] }")

(deftest a-test
    (let [response (app (-> (mock/request :post "/account" "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 } }")
                            (mock/content-type "application/json")))
          body     (:body response)]
      (is (= (:status response) 200))
      (is (= body (get-account)))))
