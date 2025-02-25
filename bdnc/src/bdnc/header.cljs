(ns bdnc.header
  (:require
   ["react-transition-group" :refer [Transition TransitionGroup]]
   [bdnc.hamburger :as hamburger]
   [bdnc.pages :as pages]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]
   [reagent.core :as r]))

(def page-title-animation-duration 333)

(defn site-title [props]
  [:div props
   [:span#site-title
    "bdnc"]])

(def transition-classes {:exited ["-translate-x-[100vw]"]
                         :exiting ["translate-x-[100vw]"]
                         :entering ["-translate-x-1/2"]
                         :entered []})

(defn page-title [props]
  (let [page-active-id @(rf/subscribe [:page-active])
        page-active (page-active-id pages/all)
        continued (:continued page-active)]
    [:div (conj props
                {:id "page-title"})
     [:div#page-title-container
      {:class ["relative", "w-dvw", "h-[1.75rem]"]}
      [:> TransitionGroup
       (for [[page-id, page] pages/all
             :let [element-id (str "page-title-" (name page-id))
                   title (:title page)
                   in (or
                        (= page-id
                           continued)
                        (and (not continued)
                             (= page-active-id
                                page-id)))]
             :when in]
         [:> Transition
          {:key page-id
           :timeout page-title-animation-duration
           :unmount-on-exit true}
          ;; Sliding Element
          (fn [s]
            (r/as-element [:div.page-title-indy {:key element-id
                                                 :id element-id
                                                 :class (concat ["absolute", "left-1/2" "-translate-x-1/2", "transition-transform", "duration-[333ms]", "ease-linear"]
                                                                ((keyword s)
                                                                 transition-classes))}
                           title]))])]]]))

(defn component [props]
  [:div#header props
   [site-title {:class ["absolute", "top-2", "left-2", "text-white", "text-sm"]}]
   [:div {:class []}
    [page-title {:class ["absolute", "left-0", "bottom-0", "text-white", "text-lg"]}]]
   [hamburger/component {:class ["absolute", "top-2", "right-2", "min-w-6", "flex", "justify-center", "cursor-pointer"]}]])
