(ns api42.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] {:status 200
               :body {
                      :name "Foo"
                      :desc "Hello API"}})
  (route/not-found "Not Found"))

(def app
  (->
   (wrap-defaults app-routes site-defaults)
   (middleware/wrap-json-body {:keywords? true})
   middleware/wrap-json-response))
   
(defn comb [k l]
  (if (= 1 k) (map vector l)
      (apply concat
             (map-indexed
              #(map (fn [x] (conj x %2))
                    (comb (dec k) (drop (inc %1) l)))
              l))))
