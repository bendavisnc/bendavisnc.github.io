(ns bdnc.pages.experience.experience
  (:require
   [bdnc.pages.experience.base :refer [comcast component* signalpath]]))

(def component
  (component* :experience {:comcast comcast
                           :signalpath signalpath}))
