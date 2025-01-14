(ns bdnc.scrolling
  (:require
   [bdnc.pages :as pages]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(defn scroll-observe-page-active! [target-selector]
  (let [target (.querySelector js/document target-selector)
        _ (when (nil? target)
            (throw (new js/Error (gstring/format "`scroll-observe!` target is null for selector `%s`."
                                                 target-selector))))
        observer (new js/IntersectionObserver (fn [entries]
                                                (doseq [entry entries]
                                                  (when (.-isIntersecting entry)
                                                    (let [target (.-target entry)
                                                          id (keyword (.-id target))]
                                                      (rf/dispatch [:page-active id])))))
                                              (clj->js {:root nil
                                                        :threshold 0.5}))]
    (.observe observer target)))

(defn scroll-observe-current-scroll-amount! [target-selector]
  (let [target (.querySelector js/document target-selector)]
    (.addEventListener target "scroll" (fn []
                                         (let [amount (/ (.-scrollTop target)
                                                         (.-scrollHeight target))]
                                           (rf/dispatch [:current-scroll-amount amount]))))))

(defn init! [& ids]
  (doseq [id ids]
    (scroll-observe-page-active! (str "#" (name id))))
  (scroll-observe-current-scroll-amount! "#main-container"))
