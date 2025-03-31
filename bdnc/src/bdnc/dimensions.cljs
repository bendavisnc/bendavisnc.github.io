(ns bdnc.dimensions
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(defn dispatch! []
  (let [width (.-innerWidth js/window)
        height (.-innerHeight js/window)]
    (if (or (nil? width)
            (nil? height))
      (println "dimensions are nil, `%s`."
               [width, height])
      ;; else
      (rf/dispatch [:dimensions
                    {:width width
                     :height height}]))))

(defn dimensions-observe! []
  (.addEventListener js/window
                     "resize"
                     (fn []
                       (dispatch!))))
(defn init! []
  (dimensions-observe!)
  (dispatch!))
