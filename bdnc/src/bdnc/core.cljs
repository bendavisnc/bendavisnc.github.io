(ns bdnc.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as reagent-dom]))

(enable-console-print!)

(defn header []
  [:div#header {:class ["bg-white", "w-dvw min-h-24 fixed top-0 left-0"]}])

(defn page-a []
  [:div#a {:class ["bg-[#4686f2]", "h-dvh"]}])

(defn page-b []
  [:div#b {:class ["bg-[#fc05f6]", "h-dvh"]}])

(defn page-c []
  [:div#c {:class ["bg-[#fecd41]", "h-dvh"]}])


(defn root []
  [:div#root-container {:class "relative"}
    [:<> 
      [header]
      [page-a]
      [page-b]
      [page-c]]])

(defn init! []
  (reagent-dom/render [root]
                      (.getElementById js/document 
                                       "app")))



(defn on-js-reload [])

(init!)