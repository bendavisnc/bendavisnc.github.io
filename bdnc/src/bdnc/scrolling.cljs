(ns bdnc.scrolling
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(def ready? (atom false))

(defn scroll-observe-page-active! [page]
  (let [target (.getElementById js/document (name page))
        _ (when (nil? target)
            (throw (new js/Error (gstring/format "`scroll-observe!` target is null for selector `%s`."
                                                 page))))
        observer (new js/IntersectionObserver (fn [entries]
                                                (if (not @ready?)
                                                  (reset! ready? true)
                                                  (doseq [entry entries]
                                                    (when (.-isIntersecting entry)
                                                      (let [target (.-target entry)
                                                            id (keyword (.-id target))]
                                                        (rf/dispatch [:page-active id]))))))
                      (clj->js {:root nil
                                :threshold 0.5}))]
    (.observe observer target)))

(defn scroll-to-page-active! [target-container]
  (let [page-active @(rf/subscribe [:page-active])
        target (.querySelector target-container (str "#" (name page-active)))]
    (.scrollTo target-container (clj->js {:left 0
                                          :top (.-offsetTop target)
                                          :behavior "smooth"}))))


(defn init! [& {:keys [container, pages]}]
  (doseq [page pages]
    (scroll-observe-page-active! page))
  (scroll-to-page-active! container))
