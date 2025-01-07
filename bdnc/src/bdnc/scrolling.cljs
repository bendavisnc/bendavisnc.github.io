(ns bdnc.scrolling
  (:require 
   [bdnc.pages :as pages]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(defn scroll-observe! [target-selector]
  (let [target (.querySelector js/document target-selector)
        _ (when (nil? target)
            (throw (new js/Error (gstring/format "`scroll-observe!` target is null for selector `%s`."
                                                 target-selector))))
        observer (new js/IntersectionObserver (fn [entries]
                                                (doseq [entry entries]
                                                  (when (.-isIntersecting entry)
                                                    (let [target (.-target entry)
                                                          id (keyword (.-id target))]
                                                      (rf/dispatch [:visible-page id])))))
                                              (clj->js {:root nil
                                                        :threshold (range 0 1 0.01)}))]
    (.observe observer target)))

(defn init! [& ids]
  (doseq [id ids]
    (scroll-observe! (str "#" (name id)))))