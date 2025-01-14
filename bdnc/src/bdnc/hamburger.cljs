(ns bdnc.hamburger
  (:require
   [re-frame.core :as rf]))

(defn visual-pending []
  [:svg {:xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 24 24", :stroke-width "1.5", :stroke "currentColor", :aria-hidden "true", :data-slot "icon", :class "w-6 h-6"}
        [:path {:stroke-linecap "round", :stroke-linejoin "round", :d "M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"}]])
(defn visual-active []
  [:span "x"])

(defn component [props]
  (let [page-active @(rf/subscribe [:page-active])
        hamburger-active? (= :navigation page-active)]
    [:a#hamburger (conj props
                        (if hamburger-active?
                          {:on-click (fn []
                                       (.back js/history))}
                          {:href "#navigation"}))
     (if hamburger-active?
       [visual-active]
       [visual-pending])]))
