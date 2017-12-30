
(ns bisection-key.util (:require [bisection-key.core :refer [mid-id max-id min-id bisect]]))

(defn key-before [dict base-key]
  (assert (string? base-key) "base-key should be string")
  (assert (map? dict) "dict should be a map")
  (let [existing-keys (vec (sort (keys dict))), keys-set (set existing-keys)]
    (assert (contains? keys-set base-key) "base-key should be existed")
    (let [position (.indexOf existing-keys base-key)]
      (bisect (if (zero? position) min-id (get existing-keys (dec position))) base-key))))

(defn key-after [dict base-key]
  (assert (string? base-key) "base-key should be string")
  (assert (map? dict) "dict should be a map")
  (let [existing-keys (vec (sort (keys dict))), keys-set (set existing-keys)]
    (assert (contains? keys-set base-key) "base-key should be existed")
    (let [position (.indexOf existing-keys base-key)]
      (bisect
       base-key
       (if (= position (dec (count existing-keys)))
         max-id
         (get existing-keys (inc position)))))))

(defn assoc-before [dict base-key v]
  (let [new-key (key-before dict base-key)] (assoc dict new-key v)))

(defn assoc-after [dict base-key v]
  (let [new-key (key-after dict base-key)] (assoc dict new-key v)))
