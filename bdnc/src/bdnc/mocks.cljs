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

(defn page-c [props]
  (page-mock (-> props
                 (assoc :id "c")
                 (update :class conj "bg-[#fecd41]"))))
