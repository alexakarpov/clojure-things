(ns graphs.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(comment
  "(def node (Node. "foo" nil)) -- will create the instance of a Node JVM class
   then, (.car node) is how you access its members (remember?)
")

(definterface INode
  (getCar [])
  (getCdr [])
  (setCar [x])
  (setCar [x]))

(deftype Node [^:volatile-mutable car ^:volatile-mutable cdr]
  INode
  (getCar [this] car)
  (getCdr [this] cdr)
  (setCar [this x] (set! car x) this)
  (setCdr [this x] (set! cdr x) this))
