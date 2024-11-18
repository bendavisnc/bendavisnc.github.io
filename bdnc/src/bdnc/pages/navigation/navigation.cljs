(ns bdnc.pages.navigation.navigation
  (:require [reagent.core :as reagent]
            ["react-router-dom" :as react-router-dom]))


(def links [{:name "about"
             :path "/about"}
            {:name "contact"
             :path "/contact"}])

(defn page []
 [:div {:id "page-navigation" 
        :class "flex flex-col"}
       [:ul {:class "w-4/5 self-center"}
            (map (fn [link]
                   [:li {:key (:name link)
                         :class "rounded my-8 py-10 text-2xl bg-blue-100"}
                        [:> react-router-dom/Link {:to (:path link)
                                                   :class "ml-2"}
                                                  (:name link)]])
                 links)]])
 
         
