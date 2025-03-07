(ns bdnc.pages.experience.circles
  (:require
   [goog.string :as gstring]
   [goog.string.format]
   [re-frame.core :as reframe]))

(defn circles-icon [count, active-index]
  (let [box-dimension 24
        max-count 10
        _ (when (> count max-count)
            (throw (new js/Error (gstring/format "Unexpected count of circles to draw (%s)." count))))
        cy (/ box-dimension 2)
        margin-percent (/ 1.0 50)
        margin (* box-dimension margin-percent)
        r (/ (- box-dimension
               (* (dec max-count) margin))
             (* 2 max-count))
        style-active {:fill "#f9eac4"}
        style-default {:fill "white"
                       :opacity (/ 2 3)}]
    [:svg {:xmlns "http://www.w3.org/2000/svg", :viewBox "0 0 24 24", :aria-hidden "true", :data-slot "icon"}
     (for [i (range count)
           :let [is-active? (= active-index i)
                 cx (+ (* (+ (* 2 r)
                             margin)
                          i)
                       (+ r
                          (* 2 r (.ceil js/Math
                                        (/ (- max-count count)
                                           2)))))]]
       [:circle {:style (if is-active? style-active style-default)
                 :key i
                 :r r
                 :cx cx
                 :cy cy}])]))

(defn circles [props, count, company]
  (let [active-index (or @(reframe/subscribe [:experience/item-detail-active company])
                         0)]
    [:div props
     [circles-icon count, active-index]]))
