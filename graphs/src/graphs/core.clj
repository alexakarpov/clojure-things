(ns graphs.core
  (:require [clojure.string :only (split triml)])
  (:gen-class))

(defn dedupe [a-seq]
  (seq (set a-seq)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [
        to_dedupe [1 2 3 4 3 4 "qwe" "asd" "qwe" "qwe"]
        ]
    (println "### Dedupe ###\n")
    (println "Input:" to_dedupe)
    (println "Result:" (dedupe to_dedupe))))

(def root {:v 5 :l nil :r nil})

(defn add-val
  ([bst val]
   (cond
     (nil? bst) {:v val :l nil :r nil}
     (>= (:v bst) val) (assoc bst :l (add-val (:l bst) val))
     :else (assoc bst :r (add-val (:r bst) val)))))

(def result (-> root
                (add-val 7)
                (add-val 9)
                (add-val 1)
                (add-val 2)
                (add-val 6)))

(defn dfs-find [root el]
  (loop [node root
         result []]
    (cond
      (= el (:v node)) (conj result el)
      (nil? (and (:l node) (:r node))) nil
      :else (recur (if (< el (:v node))
                     (:l node)
                     (:r node))
                   (conj result (:v node))))))

(defn all-perms [aseq]
  (if (<= (count aseq) 1)
    (list aseq)
    (for [c aseq
          rest (str (drop 1 aseq))]
      (str c (all-perms rest)))))

(defn find-in-bst [root el]
  (cond
    (nil? root) nil
    (> (:v root) el) (find-in-bst (:l root) el)
    (< (:v root) el) (find-in-bst (:r root) el)
    (= (:v root) el) root
    :else :error))

(defn permutations [xs]
  (if (= (count xs) 1)
    (list xs)
    (for [x xs
          y (permutations (drop 1 xs))]
      (cons x y))))


(assert (= result
   {:v 5
    :l {:v 1
        :r {:v 2
            :l nil
            :r nil}
        :l nil
        }
    :r {:v 7
        :l {:v 6
            :r nil
            :l nil}
        :r {:v 9
            :l nil
            :r nil}}}))


(defn permutations [xs]
  (if-not (seq xs)
    (list ())
    (for [x xs
          ys (permutations (for [z xs :when (not= z x)] 
                             z))]
      (conj ys x))))

(defn triplets [[a0 a1 a2] [b0 b1 b2]]
  (let [f (fn [a b]
            (let [d (- a b)]
              (cond (< d 0) :a
                    (> d 0) :b
                    (== d 0) nil)))
        ds [(f a0 b0) (f a1 b1) (f a2 b2)]
        ]
    (str (count (filter #(= % :a) ds))
         " "
         (count (filter #(= % :b) ds)))))
