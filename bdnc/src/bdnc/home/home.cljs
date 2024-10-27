(ns bdnc.home.home
  (:require [reagent.core :as reagent :refer [atom]]
            ["react-router-dom" :as react-router-dom]))

(defn home []
  (fn [] 
    (reagent/as-element [:div [:div "todo - home content"]])))
