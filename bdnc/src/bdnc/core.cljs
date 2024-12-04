(ns bdnc.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as reagent-dom]))

(enable-console-print!)


(defn root []
  [:div#a {:class ["bg-[#4686f2]", "h-dvh"]}])

(defn init! []
  (reagent-dom/render [root]
                      (.getElementById js/document 
                                       "app")))



(defn on-js-reload [])

(init!)