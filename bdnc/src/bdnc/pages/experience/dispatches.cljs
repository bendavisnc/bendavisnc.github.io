(ns bdnc.pages.experience.dispatches
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as reframe]))

(defn details-button-click [direction, company, details]
  (let [event-key (cond (= direction :next)
                        :experience/item-detail-active-next
                        (= direction :previous)
                        :experience/item-detail-active-previous
                        :else
                        (throw (new js/Error (gstring/format "Unexpected direction `%s`." direction))))]
    (reframe/dispatch [event-key company details])))

(def previous-detail-click (partial details-button-click :previous))

(def next-detail-click (partial details-button-click :next))

(defn expand-click [company, item-active]
  (fn []
    (println (gstring/format "Setting new active state, `%s` for experience item, `%s`." item-active company))
    (reframe/dispatch [:experience/item-active item-active])))

(defn details-scroll!
  "Sets up an observer to dispatch an event when a detail item is scrolled into view."
  [target-container, company]
  (println "Setting up details scroll observer!")
  (let [observer (new js/IntersectionObserver (fn [entries]
                                                (doseq [entry entries]
                                                  (when (.-isIntersecting entry)
                                                    (let [target (.-target entry)
                                                          i (js/parseInt (.getAttribute target
                                                                                        "data-i"))]
                                                      (println (gstring/format "Requesting new active state, `%s` for experience item detail, `%s`." i company))
                                                      (reframe/dispatch [:experience/item-detail-active-request company i])))))
                                              (clj->js {:root target-container
                                                        :threshold 0.5}))]
    (doseq [target (.-children target-container)]
      (.observe observer target))))
