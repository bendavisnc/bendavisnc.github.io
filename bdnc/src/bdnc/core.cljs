(ns bdnc.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as reagent-dom]
              ["react" :as react]
              ["react-router-dom" :as react-router-dom]
              [bdnc.root.root :as root]
              [bdnc.home.home :as home]
              [bdnc.contacts.contacts :as contacts]))

(enable-console-print!)


(defn init! []
  (let [router (react-router-dom/createBrowserRouter (clj->js [{:path "/"
                                                                :element (reagent/as-element [:> (root/root)])
                                                                :children [{:children [{:index true
                                                                                        :element (reagent/as-element [:> (home/home)])}
                                                                                       {:path "/contacts"
                                                                                        :element (reagent/as-element [contacts/contacts])}]}]}]))]
                                                                                    
    (reagent-dom/render (react/createElement react-router-dom/RouterProvider (clj->js {"router" router})) 
                        (.getElementById js/document 
                                         "app"))))



(defn on-js-reload [])

(init!)