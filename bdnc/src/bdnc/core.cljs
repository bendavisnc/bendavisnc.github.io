(ns bdnc.core
    (:require 
              [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as rd]
              [bdnc.home.home :as home]))

(enable-console-print!)


(defn app []
  [:div {:class "app-container"}
    [home/home]])

(defn init! []
  (rd/render [app]
             (. js/document (getElementById "app"))))



(defn on-js-reload []
  (init!))