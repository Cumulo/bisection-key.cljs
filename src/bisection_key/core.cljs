
(ns bisection-key.core (:require [clojure.string :as string] [clojure.set :as set]))

(defn bisect-vec [xs ys result]
  (if (and (empty? xs) (empty? ys))
    result
    (let [x (or (first xs) 0), y (or (first ys) 0), delta (- y x)]
      (cond
        (zero? delta) (recur (rest xs) (rest ys) (conj result x))
        (= delta 1)
          (let [rest-ys (rest ys)]
            (recur (rest xs) (cons (or (first rest-ys) 64) (rest rest-ys)) (conj result x)))
        (contains? #{2 3} delta)
          (recur (rest xs) (rest ys) (conj result (bit-shift-right (+ x y) 1)))
        :else (conj result (bit-shift-right (+ x y) 1))))))

(def dictionary
  (str "+-/" "0123456789" "ABCDEFGHIJKLMNOPQRSTUVWXYZ" "abcdefghijklmnopqrstuvwxyz"))

(def int->char-map
  (->> (string/split dictionary "") (map-indexed (fn [idx char] [idx char])) (into {})))

(def char->int-map (set/map-invert int->char-map))

(defn str->vec [x]
  (->> (string/split x "") (map (fn [char] (get char->int-map char))) (into [])))

(defn trim-right [x]
  (let [end (dec (count x))] (if (= "+" (subs x end)) (recur (subs x 0 end)) x)))

(defn vec->str [xs]
  (->> xs (map (fn [x] (get int->char-map x))) (string/join "") (trim-right)))

(defn bisect [x y]
  (assert (and (string? x) (string? y)) "[bitsect] arguments should be strings!")
  (assert (not= x y) "[bisection] keys are identical!")
  (assert (neg? (compare x y)) "[bisection] x > y")
  (let [xs (str->vec x), ys (str->vec y)] (vec->str (bisect-vec xs ys []))))

(def max-id (vec->str [64]))

(def mid-id (vec->str [32]))

(def min-id (vec->str [0]))
