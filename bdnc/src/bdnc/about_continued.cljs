(ns bdnc.about-continued
  (:require
   [bdnc.about-base :refer [component* thanks what]]))

(def component
  (component* :about-continued {:what what
                                :thanks thanks}))
