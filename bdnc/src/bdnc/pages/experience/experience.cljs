(ns bdnc.pages.experience.experience
  (:require
   [bdnc.pages.experience.companies :as companies]
   [bdnc.pages.experience.base :refer [component*]]))

(def component
  (component* :experience {:comcast companies/comcast
                           :signalpath companies/signalpath}))
