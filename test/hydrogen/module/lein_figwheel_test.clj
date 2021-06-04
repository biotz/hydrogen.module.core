(ns hydrogen.module.lein-figwheel-test
  "This namespace tests assuming the deprecated lein-figwheel being used."
  (:require [clojure.test :refer :all]
            [duct.core :as core]
            [hydrogen.module.core :as sut]
            [integrant.core :as ig]))

(core/load-hierarchy)

(def base-development-config
  {:duct.profile/base {:duct.core/project-ns 'foo.bar
                       :duct.core/environment :development}
   :hydrogen.module/core {}})

(deftest module-test
  (testing "blank development config"
    (is (= {:duct.core/project-ns 'foo.bar
            :duct.core/environment :development
            :duct.server/figwheel {:css-dirs ["target/resources/foo/bar/public/css"]
                                   :builds [{:id "dev"
                                             :figwheel {:on-jsload "foo/bar.client/mount-root"}
                                             :source-paths ["dev/src" "src"]
                                             :build-options {:main 'foo.bar.client.main
                                                             :output-to "target/resources/foo/bar/public/js/main.js"
                                                             :output-dir "target/resources/foo/bar/public/js"
                                                             :asset-path "/js"
                                                             :closure-defines {"re_frame.trace.trace_enabled_QMARK_" true}
                                                             :verbose false
                                                             :preloads ['day8.re-frame-10x.preload]
                                                             :optimizations :none
                                                             :parallel-build true
                                                             :externs ["src/foo/bar/client/externs.js"]}}]}}
           (core/build-config base-development-config))))

  (testing "custom externs that are environment-specific"
    (let [config (core/build-config (merge base-development-config
                                           {:hydrogen.module/core {:externs-paths {:production ["a/b/externs.js"]
                                                                                   :development ["x/y/another-externs.js"]}}}))
          actual-prod-externs-paths (get-in config [:duct.server/figwheel :builds 0 :build-options :externs])]
      (is (= actual-prod-externs-paths ["x/y/another-externs.js"])))))
