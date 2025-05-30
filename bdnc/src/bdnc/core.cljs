(ns bdnc.core
  {:figwheel-hooks true}
  (:require-macros [secretary.core :refer [defroute]])
  (:require
   [bdnc.back :as back]
   [bdnc.background :as background]
   [bdnc.dimensions :as dimensions]
   [goog.events :as events]
   [bdnc.events]
   [bdnc.header.header :as header]
   [bdnc.mocks :as mocks]
   [bdnc.pages :as pages]
   [bdnc.pages.about.about :as about]
   [bdnc.pages.about.about-continued :as about-continued]
   [bdnc.pages.contact.contact :as contact]
   [bdnc.pages.experience.events]
   [bdnc.pages.experience.experience :as experience]
   [bdnc.pages.experience.experience-continued :as experience-continued]
   [bdnc.pages.experience.subscriptions]
   [bdnc.pages.navigation.navigation :as navigation]
   [bdnc.progress-circles :as progress-circles]
   [bdnc.scrolling :as scrolling]
   [bdnc.subscriptions]
   [secretary.core :as secretary]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]
   [reagent.core :as r :refer [atom]]
   [reagent.dom.client :as rdomc])
  (:import [goog History]
           [goog.history EventType]))

(enable-console-print!)

(defroute "/:page-id-s" [page-id-s]
  (when-let [page-id (some-> page-id-s keyword)]
    (do (println (gstring/format "Navigating to `%s`" page-id))
        (rf/dispatch [:page-active page-id]))))

(defonce history
  (doto (History.)
    (events/listen EventType.NAVIGATE
                  (fn [^js/goog.History.Event event] 
                    (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(def page-props
  {:class ["flex", "h-dvh", "justify-center", "overflow-hidden", "snap-start", "relative"]})

(def pages-and-props
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

(defn pages-progress-circles [props]
  (let [phi 1.618033988749
        opacity-active (/ 1 2)
        opacity-default (/ (/ opacity-active phi) phi)]
    [:div props
     [progress-circles/component {:class ["m-auto", "w-[12rem]", "h-auto"]}
      (count pages-and-props)
      [:page-active-index]
      {:fill "black"
       :opacity opacity-active}
      {:fill "black"
       :opacity opacity-default}]]))

(defn root []
  [:div#root-container {:class ["relative" "w-dvw", "h-dvh", "overflow-hidden"]}
   [background/component
    {:class ["w-dvw", "h-dvh", "absolute", "z-[-99]"]}
    {:image "./images/bg-hermansyah2.jpg"
     :video "./videos/bg-loop.mp4"}]
   [:div#main-container 
    {:ref (fn [target]
            (scrolling/init! :pages (keys pages/all)
                             :container target)
            (back/init!)
            (dimensions/init!))
     :class ["h-dvh", "overflow-auto", "snap-mandatory", "snap-y"]}
    [header/component {:class ["bg-[#00000010]", "2xl:bg-[#00000050]", "fixed", "flex", "h-[4rem]", "items-end" "justify-center", "left-0", "lg:h-[8rem]" "portrait:md:h-[8rem]", "top-0", "w-dvw" "z-[1]"]}]
    [pages-progress-circles {:id "pages-progress-circles"
                             :class ["fixed", "w-[100vw]", "bottom-[1rem]"]}]
    (for [page-id (keys pages/all)
          :let [{:keys [component, props]} (page-id pages-and-props)
                element-id (name page-id)]]
      [component (conj props
                       {:id element-id
                        :key element-id})])]])

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
  (mount!)
  true)

(defn ^:after-load re-render []
  (mount!))

(defonce initialized? (init!))
