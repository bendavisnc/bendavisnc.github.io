(ns bdnc.components.hamburger
  (:require [reagent.core :as reagent]
            ["react-router-dom" :as react-router-dom]))

(defn component-active [props, gs]
  [:> react-router-dom/NavLink (conj props 
                                     {:to "/contacts"
                                      :on-click (fn [] (swap! gs assoc :is-at-navigation? false)
                                                  true)})
                               [:span "x"]])
   
(defn component-pending [props, gs]
  [:> react-router-dom/NavLink (conj props 
                                     {:to "/navigation"
                                      :on-click (fn [] (swap! gs assoc :is-at-navigation? true)
                                                  true)})
                               [:svg {:xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 24 24", :stroke-width "1.5", :stroke "currentColor", :aria-hidden "true", :data-slot "icon", :class "w-6 h-6"}
                                     [:path {:stroke-linecap "round", :stroke-linejoin "round", :d "M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"}]]])
                                  

(defn component [props, gs, navigation]
  (reagent/as-element (if (:is-at-navigation? @gs)  
                        [component-active props, gs]
                        [component-pending props, gs])))
    
  