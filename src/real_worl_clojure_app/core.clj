(ns real-worl-clojure-app.core
  (:gen-class)
  (:require [real-worl-clojure-app.config :as config]
            [real-worl-clojure-app.ping :as ping]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [com.stuartsierra.component :as component]
            [real-worl-clojure-app.components.example-component :as example-component]))

(def routes
  (route/expand-routes
   #{["/ping" :get ping/pong :route-name]}))

(defn create-server [config]
  (http/create-server
   {::http/routes routes
    ::http/type :jetty
    ::http/join? false
    ::http/port (-> config :server :port)}))

(defn start [config]
  (http/start (create-server config)))

(defn real-worl-clojure-app-system
  [config]
  (component/system-map 
   :example-component (example-component/new-example-component config)))

(defn -main
  []
  (let [system (-> (config/read-config)
                   (real-worl-clojure-app-system)
                   (component/start-system))]
    (println "Starting Real World Clojure API Service " system)
    ;; (start system)
    (.addShutdownHook 
     (Runtime/getRuntime)
     (new Thread #(component/stop-system system)))
    ))

