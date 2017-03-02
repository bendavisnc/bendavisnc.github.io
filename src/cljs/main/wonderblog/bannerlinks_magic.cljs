(ns wonderblog.bannerlinks-magic
  (:require 
    [clojure.string]
    [wonderblog.util :refer [get-page-name, set-style-property-dimension, get-style-property-dimension, get-size, get-current-link, get-all-links, currently-selected?]]
    )
  )


;;
;;
;; All of the logic for the banner links to size adjust dynamically.

(def normal-size  100) ; the default size of a link circle

(def magnify-factor 1.8) ; the scale factor for the "current" link circle

(def hover-magnify-factor 1.2) ; the scale factor for when hovering over a link circle

(defn perform-resize-transition [d3elem, target-size, & [skip-transition?]]
  (let [
      starting-size (get-size d3elem)
      ; left-target (* -1 (/ starting-size 4))
      size-dx (- starting-size target-size)
      left-target 
        (-
          (get-style-property-dimension (.node d3elem) "left")
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
        (get-size (get-current-link))
      large-size (* normal-size magnify-factor)
    ]
    (perform-resize-transition (get-current-link) large-size true)
  ))


(defn wire-hover! []
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
    ))


(defn get-link [i]
  (->
    (get-all-links)
    (aget 0)
    (aget i)))

(defn get-first-link []
  (get-link 0))

(defn get-second-link []
  (get-link 1))

(defn get-last-link []
  (get-link 2))

(defn get-left-val [e]
  (get-style-property-dimension e "left"))

(defn get-right-val [e]
  (+
    (get-left-val e)
    (get-size (.select js/d3 e))))

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
            (/ normal-size 2))
      ]
      (set-style-property-dimension 
        (get-second-link) 
        "left"
        new-left-val))))


(defn onload []
  (do
    (magnify-selected!)
    (reposition-middle-link!)
    (wire-hover!)
    ))


(js/jQuery onload) ; onload
