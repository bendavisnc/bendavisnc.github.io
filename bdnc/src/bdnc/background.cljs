(ns bdnc.background
  (:require
   ["react" :as react]
   [bdnc.dimensions :as dimensions]
   [clojure.string :as string]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as rf]
   [reagent.core :as reagent]))

(def bg-img-dimensions {:width 1080
                        :height 1920})

(defn transforms-calculated [dimensions]
  (let [phi 1.618033988749
        horizon-ratio 0.56
        image-width (:width bg-img-dimensions)
        image-height (:height bg-img-dimensions)
        device-width (:width dimensions)
        device-height (:height dimensions)
        device-a (/ device-height phi) ;; The distance where we want the horizon to be.
        img-og-a (* image-height (- 1 horizon-ratio))
        image-scale-horizon (/ device-a img-og-a)
        image-scale-width (/ device-width image-width)
        image-scale (max image-scale-horizon image-scale-width)
        image-scale-percent (* image-scale 100)
        img-a (* image-scale image-height (- 1 horizon-ratio))
        translate-x-centering (/ (- (* image-width image-scale)
                                    device-width)
                                 2)
        translate-x-scale-offset (/ (- image-width (* image-scale image-width))
                                    2)
        translate-x (* -1 (+ translate-x-centering translate-x-scale-offset))
        translate-y-scale-offset (/ (- image-height (* image-scale image-height))
                                    2)
        translate-y-horizon-offset (- img-a device-a)
        translate-y (* -1 (+ translate-y-scale-offset translate-y-horizon-offset))]
    {:translate-x translate-x
     :translate-y translate-y
     :scale image-scale-percent}))

(defn component [props, src]
  [:f> (fn []
         (let [dimensions @(rf/subscribe [:dimensions])
               container-ref (.createRef react)
               _ (react/useEffect (fn []
                                    (when (seq dimensions)
                                      (let [target (some-> (.-current container-ref)
                                                           (.querySelector "#bg-video"))
                                            _ (when (nil? target)
                                                (throw (new js/Error "`bg-video` is null.")))
                                            transforms (transforms-calculated dimensions)
                                            transforms-str (string/join
                                                             " "
                                                             (map
                                                               (fn [[k, v]]
                                                                 (cond (= k :scale)
                                                                       (gstring/format "scale(%s%)" v)
                                                                       (= k :translate-x)
                                                                       (gstring/format "translateX(%spx)" v)
                                                                       (= k :translate-y)
                                                                       (gstring/format "translateY(%spx)" v)))
                                                               transforms))
                                            _ (println (gstring/format "Dimensions dynamically set `%s` -> `%s`."
                                                                       dimensions, transforms-str))] 
                                        (set! (.-transform (.-style target))
                                              transforms-str)))
                                    js/undefined)
                                  (clj->js [dimensions]))]
           [:div#background (merge-with into {:ref container-ref}
                                             props)

            [:div#background-video-container {:class
                                              []}
             [:video {:id "bg-video"
                      :class ["object-none", "max-w-[initial]", "h-[initial]"]
                      :autoPlay true
                      :loop true
                      :muted true
                      :playsInline true}
              [:source {:src src
                        :type "video/mp4"}]]]]))])
