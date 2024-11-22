(ns bdnc.pages.about.about
  (:require [reagent.core :as reagent]
            ["react-router-dom" :as react-router-dom]))

(defn page []
 [:div {:id "page-about" 
        :class "flex flex-col"}
       [:span "Colorful web developer, experienced JVM programmer, functional programming enthusiast."]])
                

