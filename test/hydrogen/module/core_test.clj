(ns hydrogen.module.core-test
  (:require [clojure.test :refer :all]
            [duct.core :as core]
            [hydrogen.module.core :as sut]
            [integrant.core :as ig]))

(core/load-hierarchy)

(def base-production-config
  {:duct.profile/base {:duct.core/project-ns 'foo.bar
                       :duct.core/environment :production}
   :hydrogen.module/core {}})

(def base-development-config
  {:duct.profile/base {:duct.core/project-ns 'foo.bar
                       :duct.core/environment :development}
   :hydrogen.module/core {}})

(deftest module-test
  (testing "blank production config"
    (is (= {:duct.core/project-ns 'foo.bar
            :duct.core/environment :production
            :duct.compiler/cljs {:builds
                                 [{:source-paths ["src/"]
                                   :build-options
                                   {:main 'foo.bar.client
                                    :output-to "target/resources/foo/bar/public/js/main.js"
                                    :output-dir "target/resources/foo/bar/public/js"
                                    :asset-path "/js"
                                    :closure-defines {:goog.DEBUG false}
                                    :aot-cache true
                                    :verbose true
                                    :externs ["src/foo/bar/client/externs.js"]
                                    :optimizations :advanced}}]}}
           (core/build-config base-production-config))))

  (testing "blank development config"
    (is (= {:duct.core/project-ns 'foo.bar
            :duct.core/environment :development
            :duct.server/figwheel {:css-dirs ["target/resources/foo/bar/public/css"]
                                   :builds [{:id "dev"
                                             :figwheel {:on-jsload "foo/bar.client/mount-root"}
                                             :source-paths ["dev/src" "src"]
                                             :build-options {:main 'foo.bar.client
                                                             :output-to "target/resources/foo/bar/public/js/main.js"
                                                             :output-dir "target/resources/foo/bar/public/js"
                                                             :asset-path "/js"
                                                             :closure-defines {"re_frame.trace.trace_enabled_QMARK_" true}
                                                             :verbose false
                                                             :preloads ['day8.re-frame-10x.preload]
                                                             :optimizations :none
                                                             :externs ["src/foo/bar/client/externs.js"]}}]}}
           (core/build-config base-development-config))))

  (testing "custom externs"
    (let [expected-externs-paths ["a/b/externs.js"
                                  "x/another-externs.js"]
          config (core/build-config (merge base-production-config
                                           {:hydrogen.module/core {:externs-paths expected-externs-paths}}))
          actual-externs-paths (get-in config [:duct.compiler/cljs :builds 0 :build-options :externs])]
      (is (= expected-externs-paths actual-externs-paths))))

  (testing "custom externs that are environment-specific"
    (testing "for production environment"
      (let [config (core/build-config (merge base-production-config
                                             {:hydrogen.module/core {:externs-paths {:production ["a/b/externs.js"]
                                                                                     :development ["x/y/another-externs.js"]}}}))
            actual-prod-externs-paths (get-in config [:duct.compiler/cljs :builds 0 :build-options :externs])]
        (is (= actual-prod-externs-paths ["a/b/externs.js"]))))

    (testing "for development environment"
      (let [config (core/build-config (merge base-development-config
                                             {:hydrogen.module/core {:externs-paths {:production ["a/b/externs.js"]
                                                                                     :development ["x/y/another-externs.js"]}}}))
            actual-prod-externs-paths (get-in config [:duct.server/figwheel :builds 0 :build-options :externs])]
        (is (= actual-prod-externs-paths ["x/y/another-externs.js"])))))

  (testing "defaulting environment-specific externs"
    (let [config (core/build-config (merge base-production-config
                                           {:hydrogen.module/core {:externs-paths {:development ["x/y/another-externs.js"]}}}))
          actual-prod-externs-paths (get-in config [:duct.compiler/cljs :builds 0 :build-options :externs])]
      (is (= actual-prod-externs-paths ["src/foo/bar/client/externs.js"])))))
