(ns bdnc.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as reagent-dom]
              ["react" :as react]
              ["react-router-dom" :as react-router-dom]
              [bdnc.components.basic-page :as basic-page]
              [bdnc.pages.root.root :as root]
              [bdnc.pages.home.home :as home]
              [bdnc.pages.contact.contact :as contact]
              [bdnc.pages.navigation.navigation :as navigation]))

(enable-console-print!)


(defn init! []
  (let [router (react-router-dom/createBrowserRouter (clj->js [{:path "/"
                                                                :element (reagent/as-element [:> (root/page)])
                                                                :children [{:children [{:index true
                                                                                        :element (reagent/as-element [:> (basic-page/component (atom {:title "home"}) 
                                                                                                                                               [home/page])])}
                                                                                                                                           
                                                                                       {:path "/navigation"
                                                                                        :element (reagent/as-element [:> (basic-page/component (atom {:title "site navigation"}) 
                                                                                                                                               [navigation/page])])}
                                                                                       {:path "/contact"
                                                                                        :element (reagent/as-element [:> (basic-page/component (atom {:title "contact"}) 
                                                                                                                                               [contact/page])])}]}]}]))]
                                                                                                                                            
                                                                                    
    (reagent-dom/render (react/createElement react-router-dom/RouterProvider (clj->js {"router" router})) 
                        (.getElementById js/document 
                                        "app"))))



(defn on-js-reload [])

(init!)