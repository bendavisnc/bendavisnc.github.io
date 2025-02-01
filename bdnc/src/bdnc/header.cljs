(ns bdnc.header
  (:require
   [bdnc.hamburger :as hamburger]
   [bdnc.pages :as pages]
   [goog.string :as gstring]
   [goog.string.format]
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["react-transition-group" :refer [Transition]]))

(defn site-title [props]
  [:div props
   [:span#site-title
    "bdnc"]])

(def transition-classes {:exited ["-translate-x-[100vw]"]
                         :exiting ["translate-x-[100vw]"]
                         :entering ["-translate-x-1/2"]
                         :entered []})

(defn page-title [props]
  (let [animation-duration 333
        page-active @(rf/subscribe [:page-active])]
    [:div (conj props
                {:id "page-title"})
     [:div#page-title-container
      {:class ["relative", "w-dvw", "h-[1.75rem]"]}
      (for [[page-id, page] pages/all
            :let [element-id (str "page-title-" (name page-id))
                  title (:title page)
                  title-hidden (:title-hidden page)
                  in (= page-id page-active)]]
        [:> Transition
         {:key page-id
          :in in
          :timeout animation-duration
          :unmount-on-exit true}
           ;; Sliding Element
         (fn [s]
           (r/as-element [:div.page-title-indy {:key element-id
                                                :id element-id
                                                :class (concat ["absolute", "left-1/2" "-translate-x-1/2", "transition-transform", "duration-[333ms]", "ease-linear"]
                                                               ((keyword s)
                                                                transition-classes))}
                          (if-not title-hidden
                            title)]))])]]))

(defn component [props]
  [:div#header props
   [site-title {:class ["absolute", "top-2", "left-2", "text-white", "text-sm"]}]
   [:div {:class []}
    [page-title {:class ["absolute", "left-0", "bottom-0", "text-white", "text-lg"]}]]
   [hamburger/component {:class ["absolute", "top-2", "right-2", "min-w-6", "flex", "justify-center", "cursor-pointer"]}]])
