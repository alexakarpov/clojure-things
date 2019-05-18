(ns problems.trees
  (:use [clojure.test])
  (:require [clojure.string :refer [includes?]])
  (:require [clojure.data.json :as j]))

(defn fizzbuzz [n]
  (let [fizz? #(zero? (mod % 3))
        buzz? #(or
                (includes? (str %) "5")
                (zero? (mod % 5)))]
    (cond
      (and (fizz? n) (buzz? n)) "FizzBuzz"
      (fizz? n) "Fizz"
      (buzz? n) "Buzz"
      :else n)))

(deftest fizz-test
  (is (= (fizzbuzz 2)
         2))
  (is (= (fizzbuzz 3)
         "Fizz"))
  (is (= (map fizzbuzz (range 1 11))
         [1 2 "Fizz" 4 "Buzz" "Fizz" 7 8 "Fizz" "Buzz"]))
  (is (= (fizzbuzz 51)
         "FizzBuzz")))

(defn rrec [n]
  (loop
      [x n]
    (if (< x 0) "done"
        (cond
          (= x 123) (do
                      (println  "magic number!f")
                      :barf)
          (odd? x) (do
                     (println x "is odd")
                     (recur (dec x)))
          (even? x) (do
                      (println x "is even")
                      (recur (dec x)))
          :else (do
                  (println "lol?"))))))

(rrec 122)
(println "hi")

(comment
  (let [nums (range 1 101)
        fbz (map fizzbuzz nums)
        pairz (map vector nums fbz)]
    (doseq [[f s] pairz]
      (println f s)))

  (read-str (slurp "http://data.fixer.io/api/latest?access_key=a33bbf8f520b63070591137d4b05d435&symbols=USD")))

(run-tests)
