(ns bdnc.pages.about.about
  (:require
   [bdnc.pages.about.base :refer [component*]]
   [bdnc.pages.about.text :as text]))

(def component
  (component* :about {:who text/who
                      :job text/job}))
