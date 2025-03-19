(ns bdnc.progress-circles
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as reframe]))

(defn progress-circles-icon [count, active-index, style-active, style-default]
  (let [max-count 10
        _ (when (> count max-count)
            (throw (new js/Error (gstring/format "Unexpected count of `progress-circles` to draw (%s)." count))))
        box-height 8
        r (/ box-height 2)
        cy r
        phi 1.61803398875
        margin (/ (* 2 r)
                  phi)
        box-width (+ (* 2 r max-count)
                     (* margin (dec count)))
        centering-diff (.ceil js/Math (/ count
                                         2))
        centering-offset-odd (- (/ box-width 2)
                                (+ (* 2 r (dec centering-diff))
                                   (* margin (dec centering-diff))))
        centering-offset-even (+ (- (/ box-width 2)
                                    (/ (+ (* (* 2 r) count)
                                          (* margin (dec count)))
                                       2))
                                 r)
        centering-offset (if (odd? count) centering-offset-odd centering-offset-even)
        view-box (gstring/format "0 0 %s %s" box-width box-height)]
    [:svg {:xmlns "http://www.w3.org/2000/svg", :viewBox view-box, :aria-hidden "true", :data-slot "icon"}
     (for [i (range count)
           :let [is-active? (= active-index i)
                 cx (+ (* (+ (* 2 r)
                             margin)
                          i)
                       centering-offset)]]
       [:circle {:style (if is-active? style-active style-default)
                 :key i
                 :r r
                 :cx cx
                 :cy cy}])]))

(defn component [props, count, subscription-query, style-active, style-default]
  (let [active-index (or @(reframe/subscribe subscription-query)
                         0)]
    [:div props
     [progress-circles-icon count, active-index, style-active, style-default]]))
