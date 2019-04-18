(ns problems.trees)

(defrecord Tree [value left right])

(defn tnode
  ([v] (Tree. v nil nil))
  ([v l r] (Tree. v l r)))

(def root0 (tnode 132))
(def root1 (tnode 42
                 (tnode 24)
                 (tnode 47)))

(def root2 (tnode 42
                  (tnode 12
                         (tnode 2)
                         (tnode 22))
                  (tnode 49
                         (tnode 47)
                         (tnode 51
                                (tnode 50)
                                (tnode 60)))))

(defn traverse-lvr [node f]
  (if (nil? node) nil
      (let [{:keys [value left right]} node]
        (list
         (traverse-lvr left f)
         (f value)
         (traverse-lvr right f)))))

(defn are-same? [node1 node2]
  (println node1 "<COMPARE>" node2)
  (if (= nil node1 node2) (do (println "one is null")
                              true)
      (let [{:keys [value :as v1 left :as l1 right :as r1]} node1
            {:keys [value :as v2 left :as l2 right :as r2]} node2
            _ (println (format "node1: %s %s %s" v1 l1 r1))
            _ (println (format "node2: %s %s %s" v2 l2 r2))
            ]
        (cond (not (= v1 v2)) false
              (or (and (nil? l1)
                       (not (nil? l2)))
                  (and (nil? r1)
                       (not (nil? r2)))
                  (and (nil? r2)
                       (not (nil? r1)))
                  (and (nil? l2)
                       (not (nil? l2)))) false
              :else (and (are-same? l1 l2)
                         (are-same? r1 r2))))))

(are-same? (tnode 1 nil nil) nil)

(def t (tnode -191
              (tnode 374
                     (tnode -361
                            (tnode -771
                                   nil
                                   (tnode -379
                                          (tnode -154)
                                          (tnode -699)))
                            (tnode 159
                                   (tnode 900
                                          (tnode 305)
                                          (tnode -486))
                                   (tnode 200
                                          (tnode -699)
                                          (tnode 470))))
                     nil)
              nil))

(def tt1 (tnode 1
                   (tnode 2)
                   (tnode 2)))

(def tt2 (tnode 2
                (tnode 1)
                nil))


(flatten (traverse-lvr tt1 identity))
(flatten (traverse-lvr tt2 identity))


(defn rec [node target sum]
  (if (nil? node) false)
  (let [{:keys [value left right]} node
        sum1 (+ value sum)]
    (if (and (nil? left) (nil? right))
      (= sum1 target)
      (or (rec left target sum1)
          (rec right target sum1)))))

(defn hasPathWithGivenSum [t s]
  (if (nil? t) (= s 0)
      ;;else
      (rec t s 0)))

(hasPathWithGivenSum (tnode 1 (tnode 2) (tnode 3)) 4)

(hasPathWithGivenSum nil 1)
