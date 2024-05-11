(ns dev
  (:require [com.stuartsierra.component.repl :as component-repl]
            [real-worl-clojure-app.core :as core]
            [real-worl-clojure-app.config :as config]))

(component-repl/set-init
 (fn [_old-system]
   (-> (config/read-config)
       (core/real-worl-clojure-app-system))))