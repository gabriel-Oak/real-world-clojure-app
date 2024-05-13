(ns real-worl-clojure-app.components.pedestal-component
  (:require [com.stuartsierra.component :as component]
            [real-worl-clojure-app.ping :as ping]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(def routes
  (route/expand-routes
   #{["/ping" :get ping/pong :route-name :ping]}))

(defn create-server [config]
  (http/create-server
   {::http/routes routes
    ::http/type :jetty
    ::http/join? false
    ::http/port (-> config :server :port)}))


(defrecord PedestalComponent [config example-component]
  component/Lifecycle

  (start [component]
    (println "Starting PedestalComponent")
    (let [server (-> (create-server config)
                     (http/start))]
      (assoc component :server server)))

  (stop [component]
    (println "Stopping PedestalComponent")
    (when-let [server (:server component)]
      (http/stop server))
    (assoc component :server nil)))

(defn new-pedestal-component
  [config]
  (map->PedestalComponent {:config config}))
  
  