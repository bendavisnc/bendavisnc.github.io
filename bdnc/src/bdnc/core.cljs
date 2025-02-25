(ns bdnc.core
  {:figwheel-hooks true}
  (:require
   [bdnc.about :as about]
   [bdnc.about-continued :as about-continued]
   [bdnc.back :as back]
   [bdnc.background :as background]
   [bdnc.contact :as contact]
   [bdnc.dimensions :as dimensions]
   [bdnc.experience :as experience]
   [bdnc.experience-continued :as experience-continued]
   [bdnc.header :as header]
   [bdnc.mocks :as mocks]
   [bdnc.navigation :as navigation]
   [bdnc.pages :as pages]
   [bdnc.scrolling :as scrolling]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]
   [reagent.core :as r :refer [atom]]
   [reagent.dom.client :as rdomc]))

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
  :experience/detail-active
  (fn [db, [_ id]]
    (assoc db :experience/detail-active id)))

(rf/reg-event-db
  :dimensions
  (fn [db [_ dimensions]]
    (assoc db :dimensions dimensions)))

(rf/reg-sub
  :page-active
  (fn [db _]
    (:page-active db)))

(rf/reg-sub
  :current-scroll-amount
  (fn [db _]
    (:current-scroll-amount db)))

(rf/reg-sub
  :experience/detail-active
  (fn [db _]
    (:experience/detail-active db)))

(rf/reg-sub
  :dimensions
  (fn [db, _]
    (:dimensions db)))

(def page-props
  {:class ["flex", "h-dvh", "justify-center", "overflow-hidden", "snap-start", "relative"]})

(def page-content
  {:navigation {:component navigation/component
                :props page-props}
   ;; :props (update-in page-props [:class] concat ["pb-[57vh]", "items-end"])}
   :contact {:component contact/component
             :props page-props}
   :experience  {:component experience/component
                 :props page-props}
   :experience-continued  {:component experience-continued/component
                           :props page-props}
   :about  {:component about/component
            :props page-props}
   :about-continued  {:component about-continued/component
                      :props page-props}})

(defn root []
  [:div#root-container {:class ["relative" "w-dvw", "h-dvh", "overflow-hidden"]}
   [background/component
    {:class ["w-dvw", "h-dvh", "absolute", "z-[-99]"]}
    "/videos/bg-loop.mp4"]
   [:div#main-container {:class ["h-dvh", "overflow-auto", "snap-mandatory", "snap-y"]}
    [header/component {:class ["bg-[#00000010]", "fixed", "flex", "h-[4rem]", "items-end" "justify-center", "left-0", "lg:h-[8rem]" "portrait:md:h-[8rem]", "top-0", "w-dvw"]}]
    (for [page-id (keys pages/all)
          :let [{:keys [component, props]} (page-id page-content)
                element-id (name page-id)]]
      [component (conj props
                       {:id element-id
                        :key element-id})])]])

(defn after-ready! []
  (println "Initializing (`after-ready!`)!")
  (scrolling/init! :container :main-container
                   :pages (keys pages/all))
  (back/init!)
  (dimensions/init!)
  (let [page-onstart-s (.-hash (.-location js/window))]
    (if (not (empty? page-onstart-s))
      (println (gstring/format "Page on start, `%s`."
                               page-onstart-s))
      ;; else
      (do
        (println "Setting current page to home page.")
        (set! js/window.location.hash (name pages/home))))))

(defonce root-container
  (rdomc/create-root (.getElementById js/document
                                      "app")))
(defn mount! []
  (println "Mounting!")
  (rdomc/render root-container
                [root]))

(defn init! []
  (println "Initializing!")
  (rf/dispatch-sync [:initialize])
  (.addEventListener js/window
                     "load"
                     (fn []
                       ;; todo, remove
                       (.setTimeout js/window
                                    after-ready!
                                    500)))
  (mount!)
  true)

(defonce initialized? (init!))

(defn ^:after-load re-render []
  (mount!))
