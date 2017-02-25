(ns wonderblog.bannerlinks-magic
  (:require [clojure.string])
  )


;;
;;
;; All of the logic for the banner links to size adjust dynamically.

(def normal-size "5vw")

(def get-page-name 
  (memoize
    (fn []
      (last
        (clojure.string/split 
          (aget 
            (aget js/window "location")
            "pathname")
          "/")))))

(defn currently-selected? [e]
  (=
    (get-page-name)
    (->
      (.select js/d3 e)
      (.attr "href"))))

(defn get-current-link []
  (->
    (.select js/d3 ".bannerlinks")
    (.selectAll ".navlink")
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

(def normal-size-atom (atom 0))

(defn record-normal-size! [] ; super hacky
  (reset! normal-size-atom (get-size (get-current-link))))

; (defn test-normal-size [] ; super hacky
  ; (get-pixels (get-current-link)))

(defn get-normal-size [] (deref normal-size-atom))

(defn size-everything-normal! []
  (->
    (get-all-links)
    (.style "width" normal-size)
    (.style "height" normal-size)))

(defn magnify-selected! []
  (let [
      normal-size 
        (get-size (get-current-link))
      ; large-size (* normal-size 2.5)
      ; large-size (* normal-size 2)
      large-size (* normal-size 1.8)
      ; large-size (* normal-size 1.5)
    ]
    (->
      (get-current-link)
      (.style "width" (str normal-size "px"))
      (.style "height" (str normal-size "px"))
      (.transition)
      (.duration 500)
      (.style "width" (str large-size "px"))
      (.style "height" (str large-size "px")))
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
              (->
                d3this
                (.transition)
                (.duration 250)
                (.style "width" (str hover-size "px"))
                (.style "height" (str hover-size "px"))))))))
    (.on "mouseleave"
      (fn [e]
        (this-as thiz
          (when (not (currently-selected? thiz))
            (let [
                d3this (.select js/d3 thiz)
                normal-size 
                  (get-normal-size)
              ]
              (->
                d3this
                (.transition)
                ; (.duration 500)
                (.duration 250)
                (.style "width" (str normal-size "px"))
                (.style "height" (str normal-size "px"))))))))
    ))

(defn onload []
  (do
    (size-everything-normal!)
    (record-normal-size!)
    (magnify-selected!)
    (wire-hover!)
    ))


(js/jQuery onload) ; onload
