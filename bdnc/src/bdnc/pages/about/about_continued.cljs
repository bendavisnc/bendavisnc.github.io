(ns bdnc.pages.about.about-continued
  (:require
   [bdnc.pages.about.base :refer [component* joy thanks what]]))

(def component
  (component* :about-continued {:joy joy
                                :what what
                                :thanks thanks}))
