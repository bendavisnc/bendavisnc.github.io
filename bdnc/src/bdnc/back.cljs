(ns bdnc.back
  (:require
   [bdnc.pages :as pages]
   [goog.string :as gstring]
   [goog.string.format]))

(defn back-observe! []
  (.addEventListener
    js/window
    "hashchange"
    (fn []
      (let [page-active (keyword (subs (.-hash (.-location js/window))
                                       1))
            _ (when-not (some #{page-active} (keys pages/all))
                (throw (new js/Error (gstring/format "Invalid key referenced during `hashchange` based page navigation, `%s`."
                                                     page-active))))]
        (.scrollIntoView (.getElementById js/document
                                          (name page-active)))))))
(defn init! []
  (back-observe!))
