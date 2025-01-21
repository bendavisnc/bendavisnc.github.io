(ns bdnc.orientation
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(defn orientation-from-body []
  (let [body (.-body js/document)
        w (.-clientWidth body)
        h (.-clientHeight body)] 
    (if (>= h w)
      :portrait
      :landscape)))

;; https://stackoverflow.com/questions/5498934/detect-change-in-orientation-using-javascript
(defn orientation-observe! []
  (.addListener (.matchMedia js/window "(orientation: portrait)")
                (fn [m]
                  (let [orientation (if (.-matches m)
                                      :portrait
                                      :landscape)]
                    (println (gstring/format "Setting orientation to `%s`."
                                             orientation))
                    (rf/dispatch [:orientation orientation])))))

(defn init! []
  (rf/dispatch [:orientation (orientation-from-body)])
  (orientation-observe!))
