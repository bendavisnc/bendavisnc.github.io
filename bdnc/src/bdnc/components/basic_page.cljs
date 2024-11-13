(ns bdnc.components.basic-page
  [:require [reagent.core :as reagent]
            [bdnc.components.header :as header]
            [bdnc.components.hamburger :as hamburger]
            ["react-router-dom" :as react-router-dom]])

(defn component [gs, child]
  (fn []
    (let [navigation (react-router-dom/useNavigation)]
      (reagent/as-element [:div {:id "basic-page"}
                                [header/component gs, navigation, hamburger/component]
                                child]))))
