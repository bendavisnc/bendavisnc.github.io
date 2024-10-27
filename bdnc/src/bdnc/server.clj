(ns bdnc.server
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [file-response]]))

(defroutes handler
  (GET "/*" []  (slurp "./resources/public/index.html"))
  (route/not-found "<h1>Page not found</h1>"))