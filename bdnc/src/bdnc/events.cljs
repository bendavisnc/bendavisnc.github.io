(ns bdnc.events
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(rf/reg-event-db
  :initialize
  (fn [_ _]
    {:page-active :contact}))

(rf/reg-event-db
  :page-active
  (fn [db [_ id]]
    (-> db
        (assoc :page-active id)
        (assoc :experience/item-active nil)
        (assoc :experience/item-detail-active nil))))

(rf/reg-event-db
  :current-scroll-amount
  (fn [db [_ i]]
    (assoc db :current-scroll-amount i)))

(rf/reg-event-db
  :dimensions
  (fn [db [_ dimensions]]
    (assoc db :dimensions dimensions)))
