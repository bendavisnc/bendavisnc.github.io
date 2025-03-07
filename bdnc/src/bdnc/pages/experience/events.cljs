(ns bdnc.pages.experience.events
  (:require
   [bdnc.pages :as pbdnc.pages]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(rf/reg-event-db
  :experience/item-active
  (fn [db, [_ id]]
    (-> db
        (assoc :experience/item-active id)
        (assoc :experience/item-detail-active nil))))

(rf/reg-event-db
  :experience/item-detail-active-request
  (fn [db, [event company i]]
    (if-not (= (:experience/item-active db) company)
      db
      (do
        (println (gstring/format "Setting new active detail at index, `%s` (%s)" i company))
        (assoc-in db [event company] i)))))

(rf/reg-event-db
  :experience/item-detail-active-next
  (fn [db, [_, company, details]]
    (if-not (= (:experience/item-active db) company)
      db
      (update-in
        db
        [:experience/item-detail-active company]
        (fn [i]
          (if (= i (dec (count details)))
            0
            (inc i)))))))
