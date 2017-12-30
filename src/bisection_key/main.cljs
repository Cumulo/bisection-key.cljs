
(ns bisection-key.main
  (:require [cljs.reader :refer [read-string]]
            [bisection-key.core :refer [bisect min-id max-id mid-id]]))

(defn compare-random-ids []
  (loop [i 0, x mid-id]
    (let [yes? (> (js/Math.random) 0.5), new-id (if yes? (bisect x max-id) (bisect min-id x))]
      (println "random:" yes? (= -1 (compare x new-id)) x new-id)
      (if (< i 10) (recur (inc i) new-id)))))

(defn list-appending-results []
  (loop [i 0, x mid-id]
    (let [new-id (bisect x max-id)]
      (println "generating id:" new-id)
      (if (<= i 40) (recur (inc i) new-id) x))))

(defn run-bisection! [] (comment compare-random-ids) (comment list-appending-results))

(defn main! [] (run-bisection!) (println "App started."))

(defn reload! [] (run-bisection!) (println "Code updated."))
