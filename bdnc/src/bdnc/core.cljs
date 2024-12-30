(ns bdnc.core
  (:require
   [bdnc.contact :as contact]
   [bdnc.experience :as experience]
   [bdnc.header :as header]
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
    {:visible-page :contact}))

(rf/reg-event-db
  :visible-page
  (fn [db [_ id]]
    (-> db
        (assoc :visible-page id)
        (assoc :hamburger-active? (= id :navigation)))))

(rf/reg-event-db
  :visible?
  (fn [db [_ id]]
    (update-in db
               [:visible? id]
               not)))

(rf/reg-event-db
  :hamburger-clicked
  (fn [db [_]]
    (update db :hamburger-active? not)))

(rf/reg-sub
  :title
  (fn [_, _]
    (rf/subscribe [:visible-page]))
  (fn [visible-page, _]
    (let [title (:title (visible-page pages/all))
          _ (when-not title
              (.warn js/console (gstring/format "No title for `%s`."
                                                visible-page)))]
      title)))
(rf/reg-sub
  :hamburger-active?
  (fn [db _]
    (:hamburger-active? db)))

(rf/reg-sub
  :visible-page
  (fn [db _]
    (:visible-page db)))

(rf/reg-sub
  :visible?
  (fn [db, [_, id]]
    (println [db, id])
    (get-in db [:visible? id])))

(defn page-mock [props]
  [:div (update props
                :class
                conj
                "h-dvh", "flex", "justify-center", "items-center")
   [:div.mock (gstring/format "todo, %s content"
                              (:id props))]])

(defn page-c [props]
  (page-mock (-> props
                 (assoc :id "c-page")
                 (update :class conj "bg-[#fecd41]"))))

(defn root []
  [:div#root-container {:class ["relative" "w-dvw", "h-dvh", "bg-[url('/images/beach.png')]", "bg-cover", "overflow-hidden"]}
   [:div#main-container {:class ["overflow-auto", "h-dvh", "snap-y", "snap-mandatory"]}
    [:<> [header/component {:class ["w-dvw", "min-h-24", "fixed", "top-0", "left-0", "bg-[#00000010]", "flex", "justify-center", "items-end"]}]
         [navigation/component {:class ["h-dvh", "flex", "justify-center", "pt-32", "snap-start"]}]
         [contact/component {:class ["h-dvh", "flex", "justify-center", "pt-32", "snap-start"]}]
         [experience/component {:class ["h-dvh", "flex", "justify-center", "pt-32", "snap-start"]}]
         [page-c {:class ["snap-start"]}]]]])

(defn init! []
  (rf/dispatch-sync [:initialize])
  (reagent-dom/render [root]
                      (.getElementById js/document
                                       "app"))
  (doseq [id ["#navigation", "#contact", "#experience", "#c-page"]]
    (scroll-observe! id))
  (set! js/window.location.hash "contact"))

(defn on-js-reload [])

(init!)
