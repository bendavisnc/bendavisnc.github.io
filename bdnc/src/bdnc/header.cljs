(ns bdnc.header
  (:require
   [bdnc.hamburger :as hamburger]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(defn component [props]
  (let [title @(rf/subscribe [:title])]
    [:div#header props
     [:div {:class ["flex", "flex-col", "items-center", "min-h-24", "justify-center", "relative"]}
      [:div.title-container {:class ["absolute", "bottom-0"]}
       [:span#title {:class ["text-white"]}
        title]]]
     [hamburger/component {:class ["absolute", "top-2", "right-2", "min-w-6", "flex", "justify-center", "cursor-pointer"]}]]))
