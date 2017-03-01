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

