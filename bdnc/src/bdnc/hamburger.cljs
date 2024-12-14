(ns bdnc.hamburger
  (:require [re-frame.core :as rf]))

(def svg
  [:svg {:xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 24 24", :stroke-width "1.5", :stroke "currentColor", :aria-hidden "true", :data-slot "icon", :class "w-6 h-6"}
        [:path {:stroke-linecap "round", :stroke-linejoin "round", :d "M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"}]])
                                
    
(defn component-active [props]
  [:a (conj props 
            {}) 
      [:span "x"]])

   
(defn component-pending [props]
  [:a (conj props 
            {}) 
      svg])
                                 

(defn component [props]
  (let [hamburger-active? @(rf/subscribe [:hamburger-active?])
        component-delegate (if hamburger-active? component-active component-pending)]
    [component-delegate (conj props 
                              {:on-click (fn [_] 
                                           (.scrollIntoView (.getElementById js/document "navigation")))})]))
        
  