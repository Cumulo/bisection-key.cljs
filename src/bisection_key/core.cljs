
(ns bisection-key.core (:require [clojure.string :as string] [clojure.set :as set]))

(def dictionary
  (str "+-/" "0123456789" "ABCDEFGHIJKLMNOPQRSTUVWXYZ" "abcdefghijklmnopqrstuvwxyz"))

(def int->char-map
  (->> (string/split dictionary "") (map-indexed (fn [idx char] [idx char])) (into {})))

(def char->int-map (set/map-invert int->char-map))

(defn str->vec [x]
  (->> (string/split x "") (map (fn [char] (get char->int-map char))) (into [])))

(defn bisect-vec [xs ys result]
  (if (and (empty? xs) (empty? ys))
    (throw (js/Error. "[bisection] lack of information!"))
    (let [x (or (first xs) 0), y (or (first ys) 0)]
      (cond
        (= x y) (recur (rest xs) (rest ys) (conj result x))
        (= 1 (- y x)) (conj result x 32)
        :else (conj result (js/Math.round (bit-shift-right (+ x y) 1)))))))

(defn vec->str [xs] (->> xs (map (fn [x] (get int->char-map x))) (string/join "")))

(defn bisect [ x y]
  (assert (and (string? x) (string? y)) "[bitsect] arguments should be strings!")
  (assert (not= x y) "[bisection] keys are identical!")
  (assert (neg? (compare x y)) "[bisection] x > y")
  (let [xs (str->vec x), ys (str->vec y)] (vec->str (bisect-vec xs ys []))))

(def max-id (vec->str [64]))

(def min-id (vec->str [0]))

(def mid-id (vec->str [32]))
