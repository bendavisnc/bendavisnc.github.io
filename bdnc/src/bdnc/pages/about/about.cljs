(ns bdnc.pages.about.about
  (:require
   [bdnc.pages.about.base :refer [component* job joy who]]))

(def component
  (component* :about {:who who
                      :job job}))