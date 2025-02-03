(ns bdnc.background
  (:require
   [re-frame.core :as rf]
   [reagent.core :as reagent]))

(defn component [props, src]
  (reagent/create-class
    {:component-did-update (fn [_]
                             (let [target (.getElementById js/document "bg-video")]
                               (.load target)))
     :render (fn []
               (let [orientation @(rf/subscribe [:orientation])]
                 [:div#background (update-in props
                                             [:class]
                                             concat 
                                             [(name orientation)])
                  [:video {:id "bg-video"
                           :class ["object-cover", "w-dvw", "h-dvh"]
                           :autoPlay true
                           :loop true
                           :muted true
                           :playsInline true}
                   [:source {:src (orientation src)
                             :type "video/mp4"}]]]))}))
