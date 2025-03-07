(ns bdnc.pages.experience.subscriptions
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
  :experience/item-active
  (fn [db _]
    (:experience/item-active db)))

(rf/reg-sub
  :experience/item-detail-active
  (fn [db [k1, k2]]
    (get-in db [k1, k2])))
