(ns bdnc.root.root
  (:require [reagent.core :as reagent :refer [atom]]
            ["react-router-dom" :as react-router-dom]))

(defn root []
  (fn []
    (let [_ (react-router-dom/useNavigation)]
      (reagent/as-element [:div {:class "root-container"}
                            [:> react-router-dom/Outlet]]))))

