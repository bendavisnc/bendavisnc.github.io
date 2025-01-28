(ns bdnc.experience-continued
  (:require
   [bdnc.experience-base :refer [cargosphere component* hcl sciquest]]))

(def component
  (component* :experience-continued {:sciquest sciquest
                                     :cargosphere cargosphere
                                     :hcl hcl}))
