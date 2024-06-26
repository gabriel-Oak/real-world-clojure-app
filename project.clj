(defproject real-worl-clojure-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [aero "1.1.6"]
                 [io.pedestal/pedestal.jetty "0.6.1"]
                 [org.slf4j/slf4j-simple "2.0.9"]
                 [com.stuartsierra/component "1.1.0"]
                 [com.stuartsierra/component.repl "0.2.0"]]
  :main ^:skip-aot real-worl-clojure-app.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:main ^:skip-aot dev}}
  :source-paths ["src" "src/main/clojure" "dev"]
  )
