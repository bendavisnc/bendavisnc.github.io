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

(def get-page-key
  (memoize
    (fn []
      (keyword
        (first
          (clojure.string/split 
            (get-page-name)
            "."))))))


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

(defn at-main-page? []
  (not
    (.empty
      (get-current-link))))

(defn get-pixels [s]
  (int 
    (first
      (clojure.string/split s "px"))))

(defn get-size [e]
  (get-pixels (.style e "width")))

(defn set-style-property-dimension [d3element, property, dim]
  (.style d3element property (str dim "px")))

(defn get-style-property-dimension [d3element, property]
  (get-pixels
    (.style d3element property)))

(defn get-banner-height []
  (->
    (.select js/d3 ".banner")
    (.node)
    (aget "offsetHeight")))

(defn mobile-sized-display? []
  (<=
    (->
      (.select js/d3 ".banner")
      (.node)
      (aget "offsetWidth"))
    1199))
    

