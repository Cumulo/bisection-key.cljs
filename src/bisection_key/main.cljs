
(ns bisection-key.main
  (:require [cljs.reader :refer [read-string]] [bisection-key.core :refer [bisect]]))

(defn run-bisection! []
  (println (bisect "1" "2"))
  (println (bisect "1" "3"))
  (println (bisect "1" "4"))
  (println (bisect "1" "5"))
  (println (bisect "11" "12"))
  (println (bisect "11" "13"))
  (println (bisect "11" "14"))
  (println (bisect "11" "15")))

(defn main! [] (run-bisection!) (println "App started."))

(defn reload! [] (run-bisection!) (println "Code updated."))

(set! (.-onload js/window) main!)
