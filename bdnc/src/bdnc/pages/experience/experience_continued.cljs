(ns bdnc.pages.experience.experience-continued
  (:require
   [bdnc.pages.experience.companies :as companies]
   [bdnc.pages.experience.base :refer [component*]]))

(def component
  (component* :experience-continued {:sciquest companies/sciquest
                                     :cargosphere companies/cargosphere
                                     :hcl companies/hcl}))
