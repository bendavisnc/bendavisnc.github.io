(ns bdnc.experience
  (:require
   [bdnc.experience-base :refer [comcast component* icidigital signalpath]]))

(def component
  (component* :experience {:comcast comcast
                           :signalpath signalpath
                           :icidigital icidigital}))
