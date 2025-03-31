(ns bdnc.pages.about.about-continued
  (:require
   [bdnc.pages.about.base :refer [component*]]
   [bdnc.pages.about.text :as text]))

(def component
  (component* :about-continued {:joy text/joy
                                :what text/what
                                :thanks text/thanks}))
