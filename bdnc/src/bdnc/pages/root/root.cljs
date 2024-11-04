(ns bdnc.pages.root.root
  (:require [reagent.core :as reagent :refer [atom]]
            ["react-router-dom" :as react-router-dom]))

(defn page []
  (fn []
    (let [_ (react-router-dom/useNavigation)]
      (reagent/as-element [:div {:id "page-root"}
                            [:> react-router-dom/Outlet]]))))

