(ns bdnc.background
  (:require
   [reagent.core :as reagent]
   [re-frame.core :as rf]))

(defn component [props, src]
   (reagent/create-class
     {:component-did-update (fn [_]
                              ;; todo, remove
                              (let [target (.getElementById js/document "bg-video")]
                                (.load target)))
      :render (fn []
                (let [orientation @(rf/subscribe [:orientation])]
                  [:div#background props
                   [:video {:id "bg-video"
                            :class ["object-cover", "w-dvw", "h-dvh"]
                            :autoPlay true
                            :loop true
                            :muted true
                            :playsInline true}
                    [:source {:src (orientation src)
                              :type "video/mp4"}]]]))}))
