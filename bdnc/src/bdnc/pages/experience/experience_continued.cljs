(ns bdnc.pages.experience.experience-continued
  (:require
   [bdnc.pages.experience.base :refer [cargosphere component* hcl sciquest]]))

(def component
  (component* :experience-continued {:sciquest sciquest
                                     :cargosphere cargosphere
                                     :hcl hcl}))
