(ns bdnc.subscriptions
  (:require
   [bdnc.pages :as pages]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(rf/reg-sub
  :page-active
  (fn [db _]
    (:page-active db)))

(rf/reg-sub
  :page-active-index
  (fn [_, _]
    [(rf/subscribe [:page-active])])
  (fn [[page-active] _]
    (let [index (.indexOf (keys pages/all) page-active)]
      (if (= -1 index)
        (throw (new js/Error (gstring/format "Unexpected `page-active` key, %s" page-active)))
        index))))

(rf/reg-sub
  :current-scroll-amount
  (fn [db _]
    (:current-scroll-amount db)))

(rf/reg-sub
  :dimensions
  (fn [db, _]
    (:dimensions db)))
