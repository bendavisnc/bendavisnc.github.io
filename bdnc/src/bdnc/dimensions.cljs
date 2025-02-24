(ns bdnc.dimensions
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(defn dispatch! []
  (rf/dispatch [:dimensions
                {:width (.-innerWidth js/window)
                 :height (.-innerHeight js/window)}]))

(defn dimensions-observe! []
  (.addEventListener js/window
                     "resize"
                     (fn []
                       (dispatch!))))
(defn init! []
  (dimensions-observe!)
  (dispatch!))
