(ns bdnc.pages.contact.contact
  (:require [reagent.core :as reagent]
            ["react-router-dom" :as react-router-dom]))

(def links [{:name "github"
             :path "https://github.com/bendavisnc"}
            {:name "gmail"
             :path "mailto:bendavisnc@gmail.com"}
            {:name "linkedin"
             :path "https://www.linkedin.com/in/ben-davis-9b708548/"}])


(defn page []
 [:div {:id "page-contact" 
        :class "flex flex-col"}
       [:ul {:class "w-4/5 self-center"}
            (map (fn [link]
                   [:li {:key (:name link)
                         :class "rounded my-8 py-10 text-2xl bg-black bg-opacity-10"}
                        [:> react-router-dom/Link {:to (:path link)
                                                   :class "ml-2"}
                                                  (:name link)]])
                 links)]])
 
         

