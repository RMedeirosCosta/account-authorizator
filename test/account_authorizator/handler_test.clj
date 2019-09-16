(ns account-authorizator.handler-test
  (:require [clojure.test :refer [deftest, is, , use-fixtures]]
            [ring.mock.request :as mock]
            [account-authorizator.handler :refer [app]]
            [account-authorizator.helper.account_helper :refer [clear-database]]))

(defn get-account []
  "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 }, \"violations\": [] }")

;; FIXME Configure text fixture to clean another tests
(deftest a-test
    (let [response (app (-> (mock/request :post "/account" "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 } }")
                            (mock/content-type "application/json")))
          body     (:body response)]
      (is (= (:status response) 200))
      (is (= body (get-account)))))

(use-fixtures :each clear-database)
