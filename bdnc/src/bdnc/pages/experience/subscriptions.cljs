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

(rf/reg-sub
  :experience/top-position-memo
  (fn [db _]
    (:experience/top-position-memo db)))

(rf/reg-sub
  :experience/item-translation
  (fn [db [_, company]]
    (let [is-active? (= company (:experience/item-active db))]
      (when is-active?
        (get-in db [:experience/item-translation company])))))

(rf/reg-sub
  :experience/item-active
  (fn [db _]
    (:experience/item-active db)))

(rf/reg-sub
  :experience/item-detail-active
  (fn [db [k1, k2]]
    (get-in db [k1, k2])))