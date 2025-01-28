(ns bdnc.about
  (:require
   [bdnc.about-base :refer [component* job joy who]]))

(def component
  (component* :about {:who who
                      :job job
                      :joy joy}))
