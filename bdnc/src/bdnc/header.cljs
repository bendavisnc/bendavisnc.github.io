(ns bdnc.header
  (:require
   [bdnc.hamburger :as hamburger]
   [re-frame.core :as rf]))

(defn component [props]
  (let [title @(rf/subscribe [:title])]
    [:div#header {:class ["w-dvw", "min-h-24", "fixed", "top-0", "left-0", "bg-white", "bg-opacity-50", "flex", "justify-center", "items-end"]}
     [:span#title title]
     [hamburger/component {:class ["absolute", "top-2", "right-2", "min-w-6", "flex", "justify-center", "cursor-pointer"]}]]))
