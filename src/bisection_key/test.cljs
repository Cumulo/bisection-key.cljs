
(ns bisection-key.test
  (:require [cljs.test :refer [deftest is testing run-tests]]
            [bisection-key.core :refer [max-id min-id mid-id bisect]]
            [bisection-key.util
             :refer
             [key-before
              key-after
              assoc-before
              assoc-after
              key-prepend
              key-append
              assoc-prepend
              assoc-append
              get-min-key
              get-max-key
              key-nth
              val-nth
              assoc-nth
              assoc-before-nth
              assoc-after-nth]]))

(deftest
 test-append
 (is (= (key-append {}) mid-id))
 (is (= (key-append {"a" 1}) "m"))
 (is (= (assoc-append {"a" 1} 2) {"a" 1, "m" 2})))

(deftest
 test-assoc
 (is (= (assoc-before {"a" 1, "b" 1} "a" 2) {"a" 1, "b" 1, "G" 2}))
 (is (= (assoc-after {"a" 1, "b" 1} "a" 2) {"a" 1, "b" 1, "aT" 2})))

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
 test-get-key
 (testing "get min key" (is (= "a" (get-min-key {"a" 1, "b" 2}))))
 (testing "get max key" (is (= "b" (get-max-key {"a" 1, "b" 2}))))
 (testing "get nil" (is (= nil (get-min-key {}))) (is (= nil (get-max-key {})))))

(deftest
 test-key-after
 (is (= (key-after {"a" 1, "b" 1} "a") "aT"))
 (is (= (key-after {"a" 1, "b" 1} "b") "n")))

(deftest
 test-key-before
 (is (= (key-before {"a" 1, "b" 1} "a") "G"))
 (is (= (key-before {"a" 1, "b" 1} "b") "aT")))

(deftest
 test-prepend
 (is (= (key-prepend {}) mid-id))
 (is (= (key-prepend {"a" 1}) "G"))
 (is (= (assoc-prepend {"a" 1} 2) {"a" 1, "G" 2})))

(deftest
 test-shorten
 ()
 (is (= "c" (bisect "a34fd" "f3554")))
 (is (= "a34N" (bisect "a34fd" "a3554"))))

(deftest
 testing-nth-ops
 (let [v {"a" 1, "b" 2, "c" 3}]
   (testing
    "get key at nth"
    (is (= "a" (key-nth v 0)))
    (is (= "b" (key-nth v 1)))
    (is (= "c" (key-nth v 2)))
    (is (= nil (key-nth v 3))))
   (testing
    "get val at nth"
    (is (= 1 (val-nth v 0)))
    (is (= 2 (val-nth v 1)))
    (is (= 3 (val-nth v 2)))
    (is (= nil (val-nth v 3))))
   (testing "set value at nth" (is (= (assoc v "a" 4) (assoc-nth v 0 4))))
   (testing "set value before nth" (is (= (assoc v "aT" 4) (assoc-before-nth v 1 4))))
   (testing "set value after nth" (is (= (assoc v "bT" 4) (assoc-after-nth v 1 4))))))

(defn main! [] (run-tests))

(defn reload! [] (main!))
