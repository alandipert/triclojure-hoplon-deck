#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.2.1"

(set-env!
 :dependencies '[[tailrecursion/boot.task "2.1.0"]
                 [tailrecursion/hoplon "5.5.0"]
                 [tailrecursion/boot.notify "2.0.0-SNAPSHOT"]
                 [tailrecursion/boot.ring   "0.1.0-SNAPSHOT"]
                 [org.clojure/clojurescript "0.0-2138"]]
 :src-paths    #{"src"}
 :out-path     "resources/public")

(add-sync! (get-env :out-path) #{"resources/assets"})

(require '[tailrecursion.hoplon.boot      :refer :all]
         '[tailrecursion.boot.task.notify :refer [hear]]
         '[tailrecursion.boot.task.ring   :refer [dev-server]])

(deftask present
  "Continuously recompile and serve slides locally."
  []
  (println "Serving slides at http://localhost:8000/triclojure-deck.html")
  (comp (watch)
        (hear)
        (hoplon {:prerender false})
        (dev-server)))
