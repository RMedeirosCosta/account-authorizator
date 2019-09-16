(ns account-authorizator.handler-test
  (:require [clojure.test :refer [deftest, is, , use-fixtures]]
            [ring.mock.request :as mock]
            [account-authorizator.handler :refer [app]]
            [account-authorizator.helper.account_helper :refer [clear-database]]))

(defn get-expected-account []
  "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 }, \"violations\": [] }")

(defn get-expected-already-initialized-account []
  "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 }, \"violations\": [\"account-already-initialized\"] }")

(defn account-post-request []
  (app (-> (mock/request :post "/account" "{ \"account\": { \"activeCard\": true, \"availableLimit\": 100 } }")
           (mock/content-type "application/json"))))

(deftest post-account-without-previous-account
    (let [response (account-post-request)
          body     (:body response)]
      (is (= (:status response) 200))
      (is (= body (get-expected-account)))))

(deftest post-account-with-previous-account
    (account-post-request)
    (let [response (account-post-request)
          body     (:body response)]
      (is (= (:status response) 200))
      (is (= body (get-expected-already-initialized-account)))))

(use-fixtures :each clear-database)