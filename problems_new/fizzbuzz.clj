(ns problems.trees
  (:use [clojure.test])
  (:use [clojure.string]))

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


(let [nums (range 1 101)
      fbz (map fizzbuzz nums)
      pairz (map vector nums fbz)]
  (doseq [[f s] pairz]
    (println f s)))

(run-tests)
