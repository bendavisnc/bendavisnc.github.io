(ns bdnc.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as reagent-dom]
              [goog.string :as gstring]
              [goog.string.format]
              [re-frame.core :as rf]
              [bdnc.navigation :as navigation] 
              [bdnc.hamburger :as hamburger])) 

(enable-console-print!)

(defn get-data-attribute [node key]
  (when node
    (aget (.-dataset node) key)))

(defn scroll-observe! [target-selector]
  (let [target (.querySelector js/document target-selector)
        _ (when (nil? target)
            (throw (new js/Error "`scroll-observe!` target is null")))
        observer (new js/IntersectionObserver (fn [entries] 
                                                (doseq [entry entries
                                                        :let [entry-target (.-target entry)]]
                                                  (when (.-isIntersecting entry) 
                                                    (rf/dispatch [:visible-index (js/parseInt (get-data-attribute entry-target "index"))]))))
                                              (clj->js {:root nil 
                                                        :threshold 0.1}))]
    (.observe observer target)))

(defn title [i]
  (str "wat" i))

(rf/reg-event-db
  :initialize
  (fn [_ _]
    {}))

(rf/reg-event-db
  :visible-index
  (fn [db [_ i]]
    (-> db
        (assoc :visible-index i)
        (assoc :hamburger-active? (= i -1)))))

(rf/reg-event-db
  :hamburger-clicked
  (fn [db [_]]
    (update db :hamburger-active? not)))

(rf/reg-sub
  :title
  (fn [db _] 
    (title (:visible-index db))))

(rf/reg-sub
  :hamburger-active?
  (fn [db _] 
    (:hamburger-active? db)))

(rf/reg-sub
  :visible-index
  (fn [db _] 
    (:visible-index db)))


(defn header []
  (let [title @(rf/subscribe [:title])]
    [:div#header {:class ["w-dvw", "min-h-24", "fixed", "top-0", "left-0", "bg-white", "bg-opacity-50", "flex", "justify-center", "items-end"]}
      [:span#title title]
      [hamburger/component {:class "absolute top-2 right-2"}]])) 

(defn page [id, color, index]
  [:div {:id id
         :class [color, "h-dvh"]
         :data-index index}])

(defn page-a []
  (page "a-page" "bg-[#4686f2]" 0))

(defn page-b []
  (page "b-page" "bg-[#fc05f6]" 1))

(defn page-c []
  (page "c-page" "bg-[#fecd41]" 2))

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
