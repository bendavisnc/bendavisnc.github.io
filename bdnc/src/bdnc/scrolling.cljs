(ns bdnc.scrolling
  (:require [re-frame.core :as rf]))

(defn scroll-observe! [target-selector]
  (let [target (.querySelector js/document target-selector)
        _ (when (nil? target)
            (throw (new js/Error "`scroll-observe!` target is null")))
        observer (new js/IntersectionObserver (fn [entries]
                                                (doseq [entry entries]
                                                  (aset js/window "wut" (clj->js (range 0 1 0.01)))
                                                  (when (.-isIntersecting entry)
                                                    (let [target (.-target entry)
                                                          id (keyword (.-id target))
                                                          position (.-intersectionRatio entry)]
                                                      (rf/dispatch [:visible-page {:id id
                                                                                   :position position}])))))
                                              (clj->js {:root nil
                                                        :threshold (range 0 1 0.01)}))]
    (.observe observer target)))

(defn init! [& ids]
  (doseq [id ids]
    (scroll-observe! id)))