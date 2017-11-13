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

(deftype Node [car cdr])
