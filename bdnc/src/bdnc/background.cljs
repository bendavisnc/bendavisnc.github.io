(ns bdnc.background
  (:require
   ["react" :as react]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]
   [reagent.core :as reagent]
   [bdnc.dimensions :as dimensions]))

(defn position-wide []
  (let [phi 1.618033988749
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
    {:scale image-scale-percent
     :translate-y translate-y}))


(defn position []
  (let [phi 1.618033988749
        horizon-ratio 0.56
        image-width 1080
        image-height 1920
        device-width (.-width (.getBoundingClientRect (.-body js/document)))
        device-height (.-height (.getBoundingClientRect (.-body js/document)))
        image-scale (/ device-width image-width)
        _ (println (gstring/format "`device-height`, %s" device-height))
        ;; `device-a` - phi a where a + b = device height
        device-a (/ device-height phi)
        img-a (* image-scale image-height (- 1 horizon-ratio))
        scale (/ device-a img-a)
        scale-percent (* 100 scale)
        _ (println "waaat")
        _ (println [device-a, img-a, scale])]
    {:scale scale-percent}))

(defn component [props, src]
  [:f> (fn []
         (let [dimensions @(rf/subscribe [:dimensions])
               container-ref (.createRef react)
               _ (react/useEffect (fn []
                                    (let [target (some-> (.-current container-ref)
                                                         (.querySelector "#bg-video"))
                                          _ (when (nil? target)
                                              (throw (new js/Error "`bg-video` is null.")))]
                                      js/undefined))
                                  (clj->js [dimensions]))]
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
