(ns bdnc.background
  (:require
   ["react" :as react]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]
   [reagent.core :as reagent]))

(defn component [props, src]
  [:f> (fn []
         (let [container-ref (.createRef react)
               _ (react/useEffect (fn []
                                    (let [target (some-> (.-current container-ref)
                                                   (.querySelector "#bg-video"))
                                          _ (when (nil? target)
                                              (throw (new js/Error "`bg-video` is null.")))
                                          phi 1.618033988749
                                          horizon-ratio 0.56
                                          image-width 1080
                                          image-height 1920
                                          device-width (.-width (.getBoundingClientRect (.-body js/document)))
                                          device-height (.-height (.getBoundingClientRect (.-body js/document)))
                                          image-scale (/ device-width image-width)
                                          image-scale-percent (* image-scale 100)
                                          _ (println (gstring/format "`device-height`, %s" device-height))
                                          ;; `device-a` - phi a where a + b = device height
                                          device-a (/ device-height phi)
                                          img-a (* image-scale image-height (- 1 horizon-ratio))
                                          translate-y (- img-a device-a)]
                                      (set! (.-transform (.-style target))
                                            (gstring/format "translateY(-%spx) scale(%s%) "
                                                            translate-y
                                                            image-scale-percent))
                                      js/undefined)))]
           [:div#background (merge-with into {:ref container-ref}
                                             props)
            [:video {:id "bg-video"
                     :class ["object-none", "w-dvw"]
                     :autoPlay true
                     :loop true
                     :muted true
                     :playsInline true}
             [:source {:src src
                       :type "video/mp4"}]]]))])
