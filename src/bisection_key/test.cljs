
(ns bisection-key.test
  (:require [cljs.test :refer [deftest is testing run-tests]]
            [bisection-key.core :refer [max-id min-id mid-id bisect]]
            [bisection-key.util :refer [key-before key-after assoc-before assoc-after]]))

(deftest
 test-bisect
 (is (= (bisect "1" "2") "1T"))
 (is (= (bisect "1" "3") "2"))
 (is (= (bisect "1" "4") "2"))
 (is (= (bisect "1" "5") "3"))
 (is (= (bisect "11" "12") "11T"))
 (is (= (bisect "11" "13") "12"))
 (is (= (bisect "11" "14") "12"))
 (is (= (bisect "11" "15") "13")))

(deftest
 test-frequent-append
 (is
  (=
   (loop [i 0, x mid-id]
     (let [new-id (bisect x max-id)] (if (<= i 40) (recur (inc i) new-id) x)))
   "yyyyyyy")))

(deftest
 test-frequent-prepend
 (is
  (=
   (loop [i 0, x max-id]
     (let [new-id (bisect min-id x)] (if (<= i 40) (recur (inc i) new-id) x)))
   "++++++/")))

(deftest
 test-key-before
 (is (= (key-before {"a" 1, "b" 1} "a") "G"))
 (is (= (key-before {"a" 1, "b" 1} "b") "aT")))

(deftest
 test-key-after
 (is (= (key-after {"a" 1, "b" 1} "a") "aT"))
 (is (= (key-after {"a" 1, "b" 1} "b") "n")))

(deftest
 test-assoc
 (is (= (assoc-before {"a" 1, "b" 1} "a" 2) {"a" 1, "b" 1, "G" 2}))
 (is (= (assoc-after {"a" 1, "b" 1} "a" 2) {"a" 1, "b" 1, "aT" 2})))

(defn main! [] (run-tests))

(defn reload! [] (main!))
