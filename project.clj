(defproject hydrogen/module.core "0.1.12-SNAPSHOT"
  :description "Duct module for doing cljs-based SPA applications the Hydrogen way."
  :url "https://github.com/magnetcoop/hydrogen.module.core"
  :license {:name "Mozilla Public Licence 2.0"
            :url "https://www.mozilla.org/en-US/MPL/2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.741"]
                 [duct/core "0.8.0"]
                 [integrant "0.8.0"]]
  :deploy-repositories [["snapshots" {:url "https://clojars.org/repo"
                                      :username :env/clojars_username
                                      :password :env/clojars_password
                                      :sign-releases false}]
                        ["releases"  {:url "https://clojars.org/repo"
                                      :username :env/clojars_username
                                      :password :env/clojars_password
                                      :sign-releases false}]]
  :profiles
  {:dev          [:project/dev :profiles/dev]
   :profiles/dev {}
   :project/dev  {:plugins [[lein-cljfmt "0.6.7"]
                            [jonase/eastwood "0.3.11"]]}})
