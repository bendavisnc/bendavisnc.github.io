(ns bdnc.about-continued
  (:require
   [bdnc.about-base :refer [component* joy thanks what]]))

(def component
  (component* :about-continued {:joy joy
                                :what what
                                :thanks thanks}))
