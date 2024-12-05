(ns bdnc.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as reagent-dom]
              [goog.string :as gstring]
              [goog.string.format]
              [re-frame.core :as rf]))

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
                                                    (rf/dispatch [:visible (get-data-attribute entry-target "index")]))))
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
  :visible
  (fn [db [_ i]]
    (assoc db :title (title i))))

(rf/reg-sub
  :title
  (fn [db _] 
    (:title db)))

(defn header []
  (let [title @(rf/subscribe [:title])]
    [:div#header {:class ["bg-white", "w-dvw min-h-24 fixed top-0 left-0"]}
      [:span#title title]]))

(defn page [id, color, index]
  [:div {:id id
         :class [(gstring/format "bg-[%s]" color), "h-dvh"]
         :data-index index}])

(defn page-a []
  (page "a" "#4686f2" 0))

(defn page-b []
  (page "b" "#fc05f6" 1))

(defn page-c []
  (page "c" "#fecd41" 2))

(defn root []
  [:div#root-container {:class "relative"}
    [:<> 
      [header]
      [page-a]
      [page-b]
      [page-c]]])

(defn init! []
  (reagent-dom/render [root]
                      (.getElementById js/document 
                                       "app"))
  (doseq [id ["#a", "#b", "#c"]]
    (scroll-observe! id)))

(defn on-js-reload [])

(init!)