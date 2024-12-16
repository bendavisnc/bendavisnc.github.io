(ns bdnc.core
  (:require
   [bdnc.hamburger :as hamburger]
   [bdnc.navigation :as navigation]
   [bdnc.pages :as pages]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as reagent-dom]))

(enable-console-print!)

(defn scroll-observe! [target-selector]
  (let [target (.querySelector js/document target-selector)
        _ (when (nil? target)
            (throw (new js/Error "`scroll-observe!` target is null")))
        observer (new js/IntersectionObserver (fn [entries]
                                                (doseq [entry entries]
                                                  (when (.-isIntersecting entry)
                                                    (rf/dispatch [:visible-page (-> entry .-target .-id keyword)]))))
                                              (clj->js {:root nil
                                                        :threshold 0.1}))]
    (.observe observer target)))

(rf/reg-event-db
  :initialize
  (fn [_ _]
    {}))

(rf/reg-event-db
  :visible-page
  (fn [db [_ id]]
    (-> db
        (assoc :visible-page id)
        (assoc :hamburger-active? (= id :navigation)))))

(rf/reg-event-db
  :hamburger-clicked
  (fn [db [_]]
    (update db :hamburger-active? not)))

(rf/reg-sub
  :title
  (fn [db _]
    (-> db :visible-page pages/all :title)))

(rf/reg-sub
  :hamburger-active?
  (fn [db _]
    (:hamburger-active? db)))

(rf/reg-sub
  :visible-page
  (fn [db _]
    (:visible-page db)))

(defn header []
  (let [title @(rf/subscribe [:title])]
    [:div#header {:class ["w-dvw", "min-h-24", "fixed", "top-0", "left-0", "bg-white", "bg-opacity-50", "flex", "justify-center", "items-end"]}
     [:span#title title]
     [hamburger/component {:class ["absolute", "top-2", "right-2", "min-w-6", "flex", "justify-center", "cursor-pointer"]}]]))

(defn page [id, color]
  [:div {:id id
         :class [color, "h-dvh"]}])

(defn page-a []
  (page "a-page" "bg-[#4686f2]"))

(defn page-b []
  (page "b-page" "bg-[#fc05f6]"))

(defn page-c []
  (page "c-page" "bg-[#fecd41]"))

(defn root []
  [:div#root-container {:class "relative"}
   [:<> [header]
        [navigation/component]
        [page-a]
        [page-b]
        [page-c]]])

(defn init! []
  (reagent-dom/render [root]
                      (.getElementById js/document
                                       "app"))
  (doseq [id ["#navigation", "#a-page", "#b-page", "#c-page"]]
    (scroll-observe! id))
  (set! js/window.location.hash "a-page"))

(defn on-js-reload [])

(init!)
