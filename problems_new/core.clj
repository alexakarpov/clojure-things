(ns problems.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn atoi [a-seq]
  {:pre [(coll? a-seq) ]}
  "function to convert a list of digits to an integer number they represent"
  (let [codes (map int a-seq)
        valid-seq (filter #(and (>= % 48)
                                (<= % 57))
                          codes)
        convert #(- (int %) 48)
        ]
    (if (= (count valid-seq)
           (count codes))
      (int (apply + (map-indexed (fn [idx value]
                                   (let [len (count a-seq)
                                         p10 (dec (- len idx))]
                                     (* (Math/pow 10 p10) value)))
                                 (map convert a-seq))))
      :error)))

(defn is-palindrome? [^java.lang.String s]
  "Tests a string for whether it is a palindrome"
  (let [len (count s)]
    (if (or (= 0 len) (= 1 len))
      true
      (and (= (nth s 0) (nth s (dec len)))
           (recur (take (dec (dec len)) (drop 1 s)))))))

(defn buy-sell [stocks]
  "Given a collection of integer numbers, representing the price of the stock on consecutive days, shows which buy and sell days would yield maximum profit."
  (loop [buy-candidate {:buy 1
                        :price (first stocks)}
         deal {:buy 1
               :sell 1
               :profit 0}
         day 1
         stocks stocks]
    (if (empty? stocks) deal
        (let [price (first stocks)
              profit (- price (:price buy-candidate))]
          (recur (if (< price (:price buy-candidate))
                   {:day day :price price}
                   buy-candidate)
                 (if (> profit (:profit deal))
                   (merge buy-candidate {:sell day
                                         :profit profit})
                   deal)
                 (inc day)
                 (rest stocks))))))

(defrecord ListNode [value next])

(defn linked-list->vector [linked-list]
  (loop [ll linked-list
         acc []]
    (if (nil? ll)
      acc
      (let [{:keys [value next]} ll]
        (recur next (conj acc value))))))

(defn i-to-ll [llist i]
  (ListNode. i llist))

(defn vector->linked-list [avec]
  (reduce i-to-ll nil (reverse avec)))

(defn reverseNodesInKGroups [l k]
  (let [avec (linked-list->vector l)
        new-list (partition k k [nil] avec)
        last-k (reverse (remove nil? (last new-list)))
        ]
    (mapcat reverse
            (if (= k (count last-k))
              new-list
              (concat (butlast new-list) (list last-k))))))

(defn movies-subseq [a]
  (loop [a (seq a)
         maybe 0
         winner 0]
    (if a
      (let [x (first a)
            new-maybe (max 0 (+ maybe x))]
        (recur (next a) new-maybe (max new-maybe winner)))
      winner)))

(assert (= 11 (movies-subseq [10 -9 10])))
(assert (= 4 (movies-subseq [3 -9 2 2])))

(defn reverse-nested [a]

  )

(defn reverse-helper [substr]
  )

(defn alternatingSums [s]
  (let [teams (partition 2 2 [0] s)
        _ (println teams)
        ]
    (reduce (fn [[s1 s2] [t1 t2]]
              [(+ s1 t1) (+ s2 t2)])
            teams)))


(defn addBorder [picture]
  (let [rows (count picture)
        cols (count (first picture))
        bar (apply str (repeat (+ 2 cols) \*))]
    (flatten (conj (vector (cons bar (map #(str \* % \*)
                                          picture)))
                   bar))))

(addBorder ["qwe" "asd"])

(apply mapv vector [[1 2 3]
                    [4 5 6]
                    [7 8 9]])

;;#######################################
(defn s-diff [s1 s2]
  (if (not= (count s1)
            (count s2))
    nil
    (loop [[c1 & rs1] s1
           [c2 & rs2] s2
           diff-count 0
           diff-vals []]
      (let [dc (if (= c1 c2) diff-count (inc diff-count))
            dvs (if (= c1 c2) diff-vals (conj diff-vals [c1 c2]))]
        (if (empty? rs1) [dc dvs]
            (recur rs1 rs2 dc dvs))))))

(defn areSimilar [a b]
  (let [[c [x y]] (s-diff a b) ]
    (or (zero? c)
        (and (= 2 c)
             (= x (reverse y))))))

;; #######################################
(defn arrayChange [inputArray]
  (if (< (count inputArray) 2)
    0
    (loop [prev (first inputArray)
           curr (second inputArray)
           rx (rest (rest inputArray))
           cnt 0
           ]
      (let [nc (if (> curr prev) 0 (inc (- prev curr)))
            nprev (+ curr nc)]
        (if (empty? rx) (+ cnt nc)
            (recur nprev
                   (first rx)
                   (rest rx)
                   (+ cnt nc)))))))

(arrayChange [5 2 1])
;;#########################################
(defn palindromeRearranging [inputString]
  (let
      [len (count inputString)
       freqs (frequencies inputString)
       vs (vals freqs)
       evens (filter even? vs)
       odds (filter odd? vs)]
    (or (and (even? len)
             (every? even? vs))
        (and (odd? len)
             (= 1 (count odds))))))


(palindromeRearranging "qweqwez")

(defn areEquallyStrong [yourLeft yourRight friendsLeft friendsRight]
  (and (= (max yourRight yourLeft)
          (max friendsRight friendsLeft))
       (= (min yourRight yourLeft)
          (min friendsRight friendsLeft))))

;;############################################

(defn arrayMaximalAdjacentDifference [inputArray]
  (let [pairs (partition 2 1 inputArray)]
    (apply max (map (fn [[x y]] (Math/abs (- x y))) pairs))))

(arrayMaximalAdjacentDifference [1 2 3 6 -6 10])
