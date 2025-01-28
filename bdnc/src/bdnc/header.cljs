(ns bdnc.header
  (:require
   [bdnc.hamburger :as hamburger]
   [bdnc.pages :as pages]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]))

(defn map-range
  "Maps a number `x` from range [a, b] to range [c, d]."
  [x a b c d]
  (+ c (* (/ (- x a) (- b a)) (- d c))))

(defn site-title [props]
  [:div props
   [:span#site-title
    "bdnc"]])

(defn page-title [props]
  (let [max-scroll (* -1 100 (count pages/all))
        current-scroll-amount  @(rf/subscribe [:current-scroll-amount])]
    [:div (conj props
                {:id "page-title"
                 :style {:left (str (map-range current-scroll-amount 0 1 0 max-scroll)
                                    "vw")}})
     (for [[i, page-id] (map-indexed vector (keys pages/all))
           :let [page (page-id pages/all)
                 element-id (str "page-title-" (name page-id))
                 title (:title page)
                 title-hidden (:title-hidden page)]]
       [:div.page-title {:key element-id
                         :id element-id
                         :class ["relative", "w-[100vw]"]}
        (if-not title-hidden
          title)])]))

(defn component [props]
  [:div#header props
   [site-title {:class ["absolute", "top-2", "left-2", "text-white", "text-sm"]}]
   [:div {:class []}
    [page-title {:class ["absolute", "bottom-0", "text-white", "text-lg", "flex", "text-center"]}]]
   [hamburger/component {:class ["absolute", "top-2", "right-2", "min-w-6", "flex", "justify-center", "cursor-pointer"]}]])
