(ns wonderblog.bannerlinks-magic
  (:require 
    [clojure.string]
    [wonderblog.util :refer [get-page-key, mobile-sized-display?, get-banner-height, at-main-page?, get-page-name, set-style-property-dimension, get-style-property-dimension, get-size, get-current-link, get-all-links, currently-selected?]]
    [wonderblog.data :refer [left-positions, magnify-factor, magnify-size-as-percent, hover-magnify-factor]]
    )
  )


;;
;;
;; All of the logic for the banner links to size adjust dynamically.

; (def magnify-size-as-percent 1.9)

; (def normal-size-atom (atom nil))

; (defn get-recorded-normal-size [] (deref normal-size-atom))

(def get-normal-size
  (memoize
    (fn []
      (/
        (* magnify-size-as-percent (get-banner-height))
        magnify-factor))))


(defn perform-resize-transition [d3elem, target-size, & [skip-transition?]]
  (let [
      starting-size (get-size d3elem)
      ; left-target (* -1 (/ starting-size 4))
      size-dx (- starting-size target-size)
      left-target 
        (-
          (get-style-property-dimension d3elem "left")
          (/ size-dx -2))
    ]
    (if skip-transition?
      (->
        d3elem
        (.style "width" (str target-size "px"))
        (.style "height" (str target-size "px"))
        (.style "left" (str left-target "px"))
        )
      ;else
      (->
        d3elem
        (.transition)
        (.duration 250)
        (.style "width" (str target-size "px"))
        (.style "height" (str target-size "px"))
        (.style "left" (str left-target "px"))
        )
      )))


(defn magnify-selected! []
  (let [
      normal-size 
        (get-normal-size)
      large-size (* normal-size magnify-factor)
    ]
    (perform-resize-transition (get-current-link) large-size true)
  ))


(defn wire-hover! []
  (let [
      normal-size (get-normal-size)
    ]
    (->
      (get-all-links)
      (.on "mouseenter"
        (fn [e]
          (this-as thiz
            (when (not (currently-selected? thiz))
              (let [
                  d3this (.select js/d3 thiz)
                  hover-size (* normal-size hover-magnify-factor)
                ]
                (perform-resize-transition d3this hover-size))))))
      (.on "mouseleave"
        (fn [e]
          (this-as thiz
            (when (not (currently-selected? thiz))
              (let [
                  d3this (.select js/d3 thiz)
                ]
                (perform-resize-transition d3this normal-size))))))
      )))

(defn get-first-link []
  (.select js/d3 ".navlink-0"))

(defn get-second-link []
  (.select js/d3 ".navlink-1"))

(defn get-last-link []
  (.select js/d3 ".navlink-2"))

(defn get-left-val [d3element]
  (get-style-property-dimension d3element "left"))

(defn get-right-val [d3element]
  (+
    (get-left-val d3element)
    (get-size d3element)))

(defn reposition-middle-link! []
  (or
    (= (.node (get-current-link)) (.node (get-second-link)))
    (let [
        new-left-val
          (-
            (/
              (+
                (get-right-val (get-first-link))
                (get-left-val (get-last-link)))
              2)
            (/ (get-normal-size) 2))
      ]
      (set-style-property-dimension 
        (get-second-link) 
        "left"
        new-left-val))))

(defn resize-bannerlinks! []
  (let [
      new-normal (str (get-normal-size) "px")
    ]
    (dorun
      (map
        (fn [bannerlink]
          (->
            (.select js/d3 bannerlink)
            (.style "width" new-normal)
            (.style "height" new-normal)))
        (aget (get-all-links) 0)))))

(defn reposition-bannerlinks! []
  (let [
      position-data ((get-page-key) left-positions)
    ]
    (dorun
      (map
        (fn [bannerlink, new-left-val]
          (->
            (.select js/d3 bannerlink)
            (.style "left" (str new-left-val "px"))))
        (aget (get-all-links) 0)
        position-data
        ))))



(defn onload []
  (do
    (when (not (mobile-sized-display?))
      (resize-bannerlinks!)
      (wire-hover!)
      (when (at-main-page?)
        (reposition-bannerlinks!)
        (magnify-selected!))
      )))


(js/jQuery onload) ; onload
