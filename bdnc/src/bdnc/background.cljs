(ns bdnc.background
  (:require
   [clojure.string :as string]
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as reframe]))

(def bg-video-dimensions {:width 1080
                          :height 1920})

(def bg-image-dimensions {:width 2160
                          :height 3840})

(defn transforms-calculated [dimensions-initial, dimensions-target]
  (let [phi 1.618033988749
        horizon-ratio 0.56
        image-width (:width dimensions-initial)
        image-height (:height dimensions-initial)
        device-width (:width dimensions-target)
        device-height (:height dimensions-target)
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

(defn transforms-str [transforms]
  (string/join
    " "
    (map
      (fn [[k, v]]
        (cond (= k :scale)
              (gstring/format "scale(%s%)" v)
              (= k :translate-x)
              (gstring/format "translateX(%spx)" v)
              (= k :translate-y)
              (gstring/format "translateY(%spx)" v)))
      transforms)))

(defn apply-img-resize-transforms! [target, dimensions-initial, dimensions-target]
  (let [transforms (transforms-str (transforms-calculated dimensions-initial dimensions-target))]
    (set! (.-transform (.-style target))
          transforms)
    (println (gstring/format "Dimensions dynamically set to `%s`, `%s` -> `%s`."
                             (.-id target)
                             dimensions-target
                             transforms))))

(defn is-super-wide-width? [width]
  (> width 1536))

(defn background-video [props, src]
  [:video (merge
            props
            {:id "bg-video"
             :autoPlay true
             :loop true
             :muted true
             :playsInline true})
   [:source {:src src
             :type "video/mp4"}]])

(defn background-image [props, src]
  [:img (merge
          props
          {:id "bg-image"
           :src src})])
(defn component [props, sources]
  [:f>
   (fn []
     (let [dimensions @(reframe/subscribe [:dimensions])
           is-super-wide? (is-super-wide-width? (:width dimensions))
           bg-element-id (if is-super-wide?
                           "bg-image"
                           "bg-video")]
       [:div#background (merge props {:ref (fn [target-parent]
                                             (let [target (some-> target-parent
                                                                  (.querySelector (str "#" bg-element-id)))]
                                               (if (nil? target)
                                                 (println (gstring/format "`bg-video` not found for `%s`."
                                                                          (some-> target-parent .-id)))
                                                 (apply-img-resize-transforms!
                                                   target
                                                   (if is-super-wide?
                                                     bg-image-dimensions
                                                     bg-video-dimensions)
                                                   dimensions))))})
        [:div#background-container {}
         (if is-super-wide?
           [background-image
            {:class ["max-w-[initial]", "h-[initial]"]}
            (:image sources)]
           [background-video
            {:class ["object-none", "max-w-[initial]", "h-[initial]"]}
            (:video sources)])]]))])
