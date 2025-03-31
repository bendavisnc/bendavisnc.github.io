(ns bdnc.mocks
  (:require
   [goog.string :as gstring]
   [goog.string.format]))
(defn page-mock [props]
  [:div (update props
                :class
                conj
                "h-dvh", "flex", "justify-center", "items-center")
   [:div.mock (gstring/format "todo, %s content"
                              (:id props))]])

(defn page-a [props]
  (page-mock (conj props
                   {:id "a"})))

(defn page-c [props]
  (page-mock (conj props
                   {:id "c"})))
