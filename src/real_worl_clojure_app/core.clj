(ns real-worl-clojure-app.core
  (:gen-class)
  (:require [com.stuartsierra.component :as component] 
            [real-worl-clojure-app.components.example-component :as example-component]
            [real-worl-clojure-app.components.pedestal-component :as pedestal-component]
            [real-worl-clojure-app.config :as config]))

(defn real-worl-clojure-app-system
  [config]
  (component/system-map
   :example-component (example-component/new-example-component config)
   :pedestal-component (component/using
                        (pedestal-component/new-pedestal-component config)
                        [:example-component])))

(defn -main
  []
  (let [system (-> (config/read-config)
                   (real-worl-clojure-app-system)
                   (component/start-system))]
    (println "Starting Real World Clojure API Service " system)
    ;; (start system)
    (.addShutdownHook
     (Runtime/getRuntime)
     (new Thread #(component/stop-system system)))))

