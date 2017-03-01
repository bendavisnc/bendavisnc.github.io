(ns wonderblog.bannerlinks-magic
  (:require 
    [clojure.string]
    [wonderblog.util :refer [get-page-name]]
    )
  )


;;
;;
;; All of the logic for the banner links to size adjust dynamically.

(defn get-normal-size [] 100)

(defn currently-selected? [e]
  (=
    (get-page-name)
    (->
      (.select js/d3 e)
      (.attr "href"))))

(defn get-current-link []
  (->
    (.selectAll js/d3 ".navlink")
    (.filter
      (fn [e]
        (this-as thiz
          (currently-selected? thiz))))
  ))

(defn get-all-links []
  (->
    (.select js/d3 ".bannerlinks")
    (.selectAll ".navlink")
  ))

(defn get-pixels [s]
  (int 
    (first
      (clojure.string/split s "px"))))

(defn get-size [e]
  (get-pixels (.style e "width")))



; selection.tween("attr.fill", function() {
;   var node = this, i = d3.interpolateRgb(node.getAttribute("fill"), "blue");
;   return function(t) {
;     node.setAttribute("fill", i(t));
;   };
; });

(defn set-style-property-dimension [e, property, dim]
  (aset 
    (aget e "style")
    property
    (str dim "px")))

(defn get-style-property-dimension [e, property]
  (get-pixels
    (aget 
      (aget e "style")
      property)))

(defn perform-resize-transition [d3elem, target-size]
  (let [
      starting-size (get-size d3elem)
      ; left-target (* -1 (/ starting-size 4))
      size-dx (- starting-size target-size)
      left-target 
        (-
          (get-style-property-dimension (.node d3elem) "left")
          (/ size-dx -2))
          ; (* 1 (/ starting-size 4)))
          ; (* 1 (/ starting-size 2)))
    ]
    (->
      d3elem
      (.transition)
      (.duration 250)
      (.style "width" (str target-size "px"))
      (.style "height" (str target-size "px"))
      (.style "left" (str left-target "px"))
      )))

; (defn perform-resize-transition [d3elem, target-size]
;   (->
;     d3elem
;     (.transition)
;     (.duration 250)
;     (.tween 
;       "resize-transition"
;       (fn []
;         (this-as thiz
;           (let [
;               raw-node thiz
;               starting-size (get-size d3elem)
;               ; interp-fn (.interpolate js/d3 (get-normal-size) target-size)
;               interp-fn (.interpolate js/d3 starting-size  target-size)
;             ]
;             (fn [t]
;               (let [i-val (interp-fn t)]
;                 (do
;                   (set-style-property-dimension raw-node "width" i-val)
;                   (set-style-property-dimension raw-node "height" i-val)
;                   (set-style-property-dimension 
;                     raw-node 
;                     "left"
;                     (* -1
;                       (+
;                         (get-style-property-dimension raw-node "left")
;                         (- i-val starting-size)))
;                     )
;                   )))))))))

(defn magnify-selected! []
  (let [
      normal-size 
        (get-size (get-current-link))
      ; large-size (* normal-size 2.5)
      ; large-size (* normal-size 2)
      large-size (* normal-size 1.8)
      ; large-size (* normal-size 1.5)
    ]
    (perform-resize-transition (get-current-link) large-size)
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
                normal-size 
                  (get-normal-size)
                hover-size (* normal-size 1.2)
              ]
              (perform-resize-transition d3this hover-size))))))
    (.on "mouseleave"
      (fn [e]
        (this-as thiz
          (when (not (currently-selected? thiz))
            (let [
                d3this (.select js/d3 thiz)
                normal-size 
                  (get-normal-size)
              ]
              (perform-resize-transition d3this normal-size))))))
    ))

(defn onload []
  (do
    (magnify-selected!)
    (wire-hover!)
    ))


(js/jQuery onload) ; onload
