
(ns bisection-key.main
  (:require [cljs.reader :refer [read-string]]
            [bisection-key.core :refer [bisect min-id max-id mid-id]]))

(defn run-bisection! []
  (println (bisect "1" "2"))
  (println (bisect "1" "3"))
  (println (bisect "1" "4"))
  (println (bisect "1" "5"))
  (println (bisect "11" "12"))
  (println (bisect "11" "13"))
  (println (bisect "11" "14"))
  (println (bisect "11" "15"))
  (loop [i 0, x mid-id]
    (let [new-id (bisect x max-id)]
      (println "new:" new-id)
      (if (< i 40) (recur (inc i) new-id))))
  (loop [i 0, x mid-id]
    (let [yes? (> (js/Math.random) 0.5), new-id (if yes? (bisect x max-id) (bisect min-id x))]
      (println "random:" yes? (= -1 (compare x new-id)) x new-id)
      (if (< i 10) (recur (inc i) new-id)))))

(defn main! [] (run-bisection!) (println "App started."))

(defn reload! [] (run-bisection!) (println "Code updated."))

(set! (.-onload js/window) main!)
