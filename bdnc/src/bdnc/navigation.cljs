(ns bdnc.navigation
  (:require
   [bdnc.links :as links]))

(defn component []
  [:div#navigation
   {:id "navigation"
    :class ["h-dvh", "flex", "justify-center", "pt-32"]
    :data-index -1}
   [:ul {:class ["flex", "flex-col"]}
    (for [link links/all
          :let [name (links/name link)
                path (links/path link)
                id (str (links/name link)
                        "-link")]]
      [:li {:key id}
       [:a {:id id
            :href path}
        name]])]])
