(ns real-worl-clojure-app.config
  ;; (:gen-class) 
  (:require [aero.core :as aero]
            [clojure.java.io :as io]))

(defn read-config
  []
  (println "Reading configs from resouces/config.edn")
  (-> "config.edn"
      (io/resource)
      (aero/read-config)))