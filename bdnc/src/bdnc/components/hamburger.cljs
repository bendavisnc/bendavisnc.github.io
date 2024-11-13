(ns bdnc.components.hamburger
  (:require [reagent.core :as reagent]
            ["react-router-dom" :as react-router-dom]))

(defn is-nav-active? [navigation]
  (.dir js/console navigation)
  (println (.-location navigation)))


(defn component-active []
  [:span "x"])
   
(defn component-pending []
  [:svg {:xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 24 24", :stroke-width "1.5", :stroke "currentColor", :aria-hidden "true", :data-slot "icon", :class "w-6 h-6"}
        [:path {:stroke-linecap "round", :stroke-linejoin "round", :d "M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"}]])
   

(defn component [props, navigation]
  [:> react-router-dom/NavLink (conj props 
                                     {:to "/navigation"
                                      :on-click (fn [] (is-nav-active? navigation))})
                               (fn [params]
                                 (let [{:keys [isActive, isPending]}
                                       (js->clj params :keywordize-keys true)]

                                   (reagent/as-element (if isActive 
                                                         [component-active]
                                                         [component-pending]))))])