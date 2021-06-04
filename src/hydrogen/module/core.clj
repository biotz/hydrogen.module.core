(ns hydrogen.module.core
  (:require [duct.core :as core]
            [integrant.core :as ig]
            [hydrogen.module.util :as util]))

(defn- externs-paths [options environment]
  (when-let [externs-paths (:externs-paths options)]
    (if (map? externs-paths)
      (get externs-paths environment)
      externs-paths)))

(defn- compiler-config
  [config options]
  (let [project-ns (util/project-ns config options)
        project-dirs (util/project-dirs config options)]
    {:builds
     [{:source-paths ["src/"]
       :build-options
       {:main (symbol (str project-ns ".client.main"))
        :output-to (format "target/resources/%s/public/js/main.js" project-dirs)
        :output-dir (format "target/resources/%s/public/js" project-dirs)
        :asset-path "/js"
        :closure-defines {:goog.DEBUG false}
        :aot-cache true
        :parallel-build true
        :verbose true
        :externs (or (externs-paths options :production)
                     [(format "src/%s/client/externs.js" project-dirs)])
        :optimizations :advanced}}]}))

(defn- duct-server-figwheel-build-options [config options]
  (let [project-ns (util/project-ns config options)
        project-dirs (util/project-dirs config options)]
    {:main (symbol (str project-ns ".client.main"))
     :output-to (format "target/resources/%s/public/js/main.js" project-dirs)
     :output-dir (format "target/resources/%s/public/js" project-dirs)
     :asset-path "/js"
     :closure-defines {"re_frame.trace.trace_enabled_QMARK_" true}
     :verbose false
     :preloads ['day8.re-frame-10x.preload]
     :optimizations :none
     :parallel-build true
     :externs (or (externs-paths options :development)
                  [(format "src/%s/client/externs.js" project-dirs)])}))

(defn- lein-figwheel-config
  [config options]
  (let [project-dirs (util/project-dirs config options)]
    {:css-dirs [(format "target/resources/%s/public/css" project-dirs)]
     :builds [{:id "dev"
               :figwheel {:on-jsload (format "%s.client/mount-root" project-dirs)}
               :source-paths ["dev/src" "src"]
               :build-options (duct-server-figwheel-build-options config options)}]}))

(defn- figwheel-main-config
  [config options]
  (let [project-dirs (util/project-dirs config options)
        port (get-in options [:figwheel-main :port] 3449)
        host (get-in options [:figwheel-main :host] "0.0.0.0")
        watch-dirs (get-in options [:figwheel-main :watch-dirs])]
    {:id "dev"
     :options (duct-server-figwheel-build-options config options)
     :config (cond-> {:mode :serve
                      :open-url false
                      :ring-server-options {:port port :host host}
                      :css-dirs [(format "target/resources/%s/public/css" project-dirs)]}
               (some? watch-dirs)
               (assoc :watch-dirs watch-dirs))}))

(defn- core-config [config options]
  (let [environment (util/get-environment config options)]
    (cond-> {}

      (and (= environment :development)
           (:figwheel-main options))

      (assoc :duct.server/figwheel-main
             (figwheel-main-config config options))

      (and (= environment :development)
           (not (:figwheel-main options)))

      (assoc :duct.server/figwheel
             (lein-figwheel-config config options))

      (= environment :production)
      (assoc :duct.compiler/cljs (compiler-config config options)))))

(defmethod ig/init-key :hydrogen.module/core [_ options]
  (fn [config]
    (core/merge-configs config (core-config config options))))
