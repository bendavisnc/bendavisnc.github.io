(ns bdnc.header
  (:require
   [bdnc.hamburger :as hamburger]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(defn site-title [props]
  [:div props
   [:span#site-title {:class ["text-white", "text-sm"]}
    "bdnc"]])

(defn page-title [props]
  (let [title @(rf/subscribe [:title])]
    [:div props
     [:span#page-title {:class ["text-white", "text-lg"]}
      title]]))

(defn component [props]
  [:div#header props
   [site-title {:class ["absolute", "top-2", "left-2"]}]
   [:div {:class ["flex", "flex-col", "items-center", "min-h-24", "justify-center", "relative"]}
    [page-title {:class ["absolute", "bottom-0"]}]]
   [hamburger/component {:class ["absolute", "top-2", "right-2", "min-w-6", "flex", "justify-center", "cursor-pointer"]}]])
