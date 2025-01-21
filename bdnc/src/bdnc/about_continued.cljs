(ns bdnc.about-continued
  (:require
   [bdnc.about-generic :refer [component* thanks what]]))

(def component
  (component* :about-continued {:what what
                                :thanks thanks}))
