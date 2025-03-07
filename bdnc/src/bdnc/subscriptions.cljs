(ns bdnc.subscriptions
  (:require
   [re-frame.core :as rf]))

(rf/reg-sub
  :page-active
  (fn [db _]
    (:page-active db)))

(rf/reg-sub
  :current-scroll-amount
  (fn [db _]
    (:current-scroll-amount db)))

(rf/reg-sub
  :experience/item-active
  (fn [db _]
    (:experience/item-active db)))

(rf/reg-sub
  :experience/item-detail-active
  (fn [db [k1, k2]]
    (get-in db [k1, k2])))

(rf/reg-sub
  :dimensions
  (fn [db, _]
    (:dimensions db)))
