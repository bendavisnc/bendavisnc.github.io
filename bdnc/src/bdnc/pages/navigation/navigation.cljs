(ns bdnc.pages.navigation.navigation)

(def links [{:name "about"
             :path "/about"}
            {:name "contacts"
             :path "/contacts"}])

(defn page []
 [:div {:id "page-navigation"} 
       [:ul (map (fn [link]
                   [:li [:a {:href (:path link)}
                            (:name link)]])
                 links)]])
 
         
