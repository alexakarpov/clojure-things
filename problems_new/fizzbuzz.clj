(ns problems.trees
  (:use [clojure.test])
  (:use [clojure.string]))

(defn fizzbuzz [n]
  (let [fizz? #(zero? (mod % 3))
        buzz? #(or
                (includes? (str %) "5") 
                (zero? (mod % 5)))]
    (cond
      (and (fizz? n) (buzz? n)) [n "FizzBuzz"]
      (fizz? n) [n "Fizz"]
      (buzz? n) [n "Buzz"]
      :else [n n])))

(deftest fizz-test
  (is (= (fizzbuzz 2)
         2))
  (is (= (fizzbuzz 3)
         [3 "Fizz"]))
  (is (= (map fizzbuzz (range 1 11))
         [1 2 [3 "Fizz"] 4 [5 "Buzz"] [6 "Fizz"] 7 8 [9 "Fizz"] [10 "Buzz"]]))
  (is (= (fizzbuzz 51)
         [51 "FizzBuzz"])))

(fizzbuzz 3)

(run-tests)

(doseq [[f s] (map fizzbuzz (range 1 101))]
  (println f s))

