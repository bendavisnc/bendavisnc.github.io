(ns wonderblog.bannerlinks-magic
  (:require 
    [clojure.string]
    [wonderblog.util :refer [at-main-page?, get-page-name, set-style-property-dimension, get-style-property-dimension, get-size, get-current-link, get-all-links, currently-selected?]]
    )
  )


;;
;;
;; All of the logic for the banner links to size adjust dynamically.

(def magnify-factor 1.8) ; the scale factor for the "current" link circle

(def hover-magnify-factor 1.2) ; the scale factor for when hovering over a link circle

(def normal-size-atom (atom nil))

(defn get-recorded-normal-size [] (deref normal-size-atom))

(defn record-normal-size! []
  (reset! normal-size-atom (get-size (.select js/d3 ".navlink-0"))))

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
        (get-recorded-normal-size)
      large-size (* normal-size magnify-factor)
    ]
    (perform-resize-transition (get-current-link) large-size true)
  ))


(defn wire-hover! []
  (let [
      normal-size (get-recorded-normal-size)
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
    (= (.node (get-current-link)) (get-second-link))
    (let [
        new-left-val
          (-
            (/
              (+
                (get-right-val (get-first-link))
                (get-left-val (get-last-link)))
              2)
            (/ (get-recorded-normal-size) 2))
      ]
      (set-style-property-dimension 
        (get-second-link) 
        "left"
        new-left-val))))


(defn onload []
  (do
    (record-normal-size!)
    (wire-hover!)
    (when (at-main-page?)
      (magnify-selected!)
      (reposition-middle-link!))
    ))


; (js/jQuery onload) ; onload
