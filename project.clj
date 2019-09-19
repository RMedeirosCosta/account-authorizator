 (defproject account-authorizator "0.1.0-SNAPSHOT"
   :description "An application that authorizes a transaction for a specific account following a set of predefined rules"
   :dependencies [[org.clojure/clojure "1.10.0"]
                  [metosin/compojure-api "2.0.0-alpha30"]
                  [clj-time "0.15.2"]]
   :ring {:handler account-authorizator.handler/app}
   :uberjar-name "server.jar"
   :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]
                                  [ring/ring-mock "0.3.2"]
                                  [ring/ring-json "0.5.0"]]
                   :plugins [[lein-ring "0.12.5"]]}})
