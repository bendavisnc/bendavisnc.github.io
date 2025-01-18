(ns bdnc.background)

(defn component [props, src]
  [:div#background props
   [:video {:id "bg-video"
            :class ["object-cover", "w-dvw", "h-dvh"]
            :autoPlay true
            :loop true
            :muted true
            :playsInline true}
    [:source {:src src
              :type "video/mp4"}]]])
