(ns bdnc.pages.navigation.navigation)

(def links [{:name "about"
             :path "/about"}
            {:name "contacts"
             :path "/contacts"}])

(defn page []
 [:div {:id "page-navigation" 
        :class "flex flex-col"}
       [:ul {:class "w-4/5 self-center"}
            (map (fn [link]
                   [:li {:class "rounded my-8 py-10 text-2xl bg-blue-100"}
                        [:a {:href (:path link)
                             :class "ml-2"}
                            (:name link)]])
                 links)]])
 
         
