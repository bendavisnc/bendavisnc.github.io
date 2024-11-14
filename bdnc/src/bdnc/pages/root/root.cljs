(ns bdnc.pages.root.root
  (:require [reagent.core :as reagent :refer [atom]]
            ["react-router-dom" :as react-router-dom]))

(defn page []
  (fn []
    (reagent/as-element [:div {:id "page-root"}
                          [:> react-router-dom/Outlet]])))

