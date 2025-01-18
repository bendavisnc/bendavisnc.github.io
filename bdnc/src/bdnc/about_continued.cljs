(ns bdnc.about-continued
  (:require
   [bdnc.about-generic :refer [component* thanks what]]))

(defn component [props]
  (component* props :about-continued {:what what
                                      :thanks thanks}))
