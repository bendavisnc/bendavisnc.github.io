(ns bdnc.dimensions
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(defn dimensions-observe! []
  (.addEventListener js/window 
                     "resize"
                     (fn []
                       (rf/dispatch [:dimensions
                                     {:device-width (.-innerWidth js/window)
                                      :device-height (.-innerHeight js/window)}]))))
(defn init! []
  (rf/dispatch [:dimensions
                {:device-width (.-innerWidth js/window)
                 :device-height (.-innerHeight js/window)}])
  (dimensions-observe!))
