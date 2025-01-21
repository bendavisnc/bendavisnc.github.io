(ns bdnc.scrolling
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(defn scroll-observe-page-active! [page]
  (let [target (.getElementById js/document (name page))
        _ (when (nil? target)
            (throw (new js/Error (gstring/format "`scroll-observe!` target is null for selector `%s`."
                                                 page))))
        observer (new js/IntersectionObserver (fn [entries]
                                                (doseq [entry entries]
                                                  (when (.-isIntersecting entry)
                                                    (let [target (.-target entry)
                                                          id (keyword (.-id target))]
                                                      (rf/dispatch [:page-active id])))))
                                              (clj->js {:root nil
                                                        :threshold 0.5}))]
    (.observe observer target)))

(defn scroll-observe-current-scroll-amount! [container]
  (let [target (.getElementById js/document (name container))
        _ (when (nil? target)
            (throw (new js/Error (gstring/format "`scroll-observe!` target is null for selector `%s`."
                                                 container))))]
    (.addEventListener target "scroll" (fn []
                                         (let [amount (/ (.-scrollTop target)
                                                         (.-scrollHeight target))]
                                           (rf/dispatch [:current-scroll-amount amount]))))))

(defn init! [& {:keys [container, pages]}]
  (doseq [page pages]
    (scroll-observe-page-active! page))
  (scroll-observe-current-scroll-amount! container))
