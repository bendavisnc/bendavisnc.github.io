(ns bdnc.core
  (:require
   [bdnc.about :as about]
   [bdnc.about-continued :as about-continued]
   [bdnc.back :as back]
   [bdnc.contact :as contact]
   [bdnc.experience :as experience]
   [bdnc.header :as header]
   [bdnc.mocks :as mocks]
   [bdnc.navigation :as navigation]
   [bdnc.pages :as pages]
   [bdnc.scrolling :as scrolling]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]
   [reagent.core :as reagent :refer [atom]]
   [reagent.dom :as reagent-dom]))

(enable-console-print!)

(rf/reg-event-db
  :initialize
  (fn [_ _]
    {:page-active :contact}))

(rf/reg-event-db
  :page-active
  (fn [db [_ id]]
    (assoc db :page-active id)))

(rf/reg-event-db
  :current-scroll-amount
  (fn [db [_ i]]
    (assoc db :current-scroll-amount i)))

(rf/reg-event-db
  :visible?
  (fn [db [_ id]]
    (update-in db
               [:visible? id]
               not)))

(rf/reg-sub
  :page-active
  (fn [db _]
    (:page-active db)))

(rf/reg-sub
  :current-scroll-amount
  (fn [db _]
    (:current-scroll-amount db)))

(rf/reg-sub
  :visible?
  (fn [db, [_, id]]
    (get-in db [:visible? id])))

(def page-content
  {:navigation {:component navigation/component
                :props {:class ["h-dvh", "flex", "justify-center", "pt-32", "snap-start"]}}
   :contact {:component contact/component
             :props {:class ["h-dvh", "flex", "justify-center", "pt-32", "snap-start"]}}
   :experience  {:component experience/component
                 :props {:class ["h-dvh", "flex", "justify-center", "pt-32", "snap-start"]}}
   :about  {:component about/component
            :props {:class ["h-dvh", "flex", "justify-center", "pt-32", "snap-start"]}}
   :about-continued  {:component about-continued/component
                      :props {:class ["h-dvh", "flex", "justify-center", "pt-32", "snap-start"]}}
   :c  {:component mocks/page-c
        :props {:class ["snap-start"]}}})

(defn root []
  [:div#root-container {:class ["relative" "w-dvw", "h-dvh", "overflow-hidden"]}
   [:div#background-container {:class ["w-dvw", "h-dvh", "absolute", "z-[-99]"]}
    [:video {:id "bg-video"
             :class ["object-cover", "w-dvw", "h-dvh"]
             :autoPlay true
             :loop true
             :muted true
             :playsInline true}
     [:source {:src "/videos/bg-loop.mp4"
               :type "video/mp4"}]]]
   [:div#main-container {:class ["overflow-auto", "h-dvh", "snap-y", "snap-mandatory"]}
    [header/component {:class ["w-dvw", "min-h-24", "fixed", "top-0", "left-0", "bg-[#00000010]", "flex", "justify-center", "items-end"]}]
    (for [page-id (keys pages/all)
          :let [{:keys [component, props]} (page-id page-content)
                element-id (name page-id)]]
      [component (conj props
                       {:id element-id
                        :key element-id})])]])

(defn init! []
  (back/init!)
  (rf/dispatch-sync [:initialize])
  (reagent-dom/render [root]
                      (.getElementById js/document
                                       "app")
                      (fn []
                        (apply scrolling/init! (keys pages/all))
                        (let [page-onstart-s (.-hash (.-location js/window))]
                          (if (not (empty? page-onstart-s))
                            (println (gstring/format "Page on start, `%s`."
                                                     page-onstart-s))
                            ;; else
                            (do
                              (println "Setting current page to home page.")
                              (set! js/window.location.hash (name pages/home))))))))
                        ;; (back/init!)))

(defn on-js-reload [])

(init!)
