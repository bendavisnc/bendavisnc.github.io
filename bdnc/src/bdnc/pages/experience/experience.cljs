(ns bdnc.pages.experience.experience
  (:require
   [bdnc.pages.experience.base :refer [component*]]
   [bdnc.pages.experience.companies :as companies]))

(def component
  (component* :experience {:comcast companies/comcast
                           :signalpath companies/signalpath
                           :ici companies/icidigital}))
