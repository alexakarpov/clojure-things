(ns triplebyte.core
  (:require
   (cli4clj [cli :as cli])
   (clojure [pprint :as pprint]))
  (:gen-class))

;;; This function is just used for providing an example below.
(defn divide [numer denom]
  (/ numer denom))

(defn -main [& args]
  (cli/start-cli {:cmds {:test {:fn #(println "This is a test.")
                                :short-info "Test Command"
                                :long-info "Prints a test message to stdout."}
                         :t :test
                         :add  {:fn (fn [summand1 summand2] (+ summand1 summand2))
                                :short-info "Add two values."}
                         :a :add

                         :to-csv {:fn (fn [data]
                                        (reduce (fn [s d]
                                                  (str s "," d))
                                                (str (first data))
                                                (rest data)))
                                  :completion-hint "The data argument can be of any Clojure sequence type, e.g., [1 2 3] or (:a :b :c). Note that the list is not quoted."
                                  :short-info "Seq to CSV"
                                  :long-info "E.g.: \"to-csv [1 2 3]\""}
                         :divide {:fn divide
                                  :completion-hint :short-info
                                  :short-info "Divide two values."
                                  :long-info "The first argument will be divided by the second argument."}
                         :d :divide
                         :print {:fn (fn [arg & opt-args]
                                       (print "Arg-type:" (type arg) "Arg: ")
                                       (pprint/pprint arg)
                                       (print "Opt-args: ")
                                       (pprint/pprint opt-args))
                                 :short-info "Pretty print the supplied arguments."
                                 :long-ingo "This function pretty prints its supplied arguments. It takes at least one argument."}
                         :p :print}}))

(defn foo []
  42)


(defn queue
  ([]
   (clojure.lang.PersistentQueue/EMPTY))
  ([coll]
   (reduce conj clojure.lang.PersistentQueue/EMPTY coll)))
