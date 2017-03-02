(ns wonderblog.util
  (:require [clojure.string])
  )

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


(defn get-all-links []
  (->
    (.selectAll js/d3 ".navlink")
  ))

(defn get-current-link []
  (->
    (get-all-links)
    (.filter
      (fn [e]
        (this-as thiz
          (currently-selected? thiz))))
  ))

(defn get-pixels [s]
  (int 
    (first
      (clojure.string/split s "px"))))

(defn get-size [e]
  (get-pixels (.style e "width")))

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
