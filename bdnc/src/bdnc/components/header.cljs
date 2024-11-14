(ns bdnc.components.header) 

(defn component [gs, navigation, hamburger]
  [:header {:class "bg-blue-200 "}
   [:nav {:id "nav"
          :class "relative flex justify-center items-end min-h-24"}
         [:a {:href "#"
              :class "text-white text-center mb-2"}
             [:span (:title @gs)]]
         [:> (hamburger {:class "absolute top-2 right-2"} 
                    gs
                    navigation)]]])
