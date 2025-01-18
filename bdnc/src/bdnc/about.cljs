(ns bdnc.about
  (:require
   [bdnc.about-generic :refer [component* job joy who]]))

(defn component [props]
  (component* props :about {:who who
                            :job job
                            :joy joy}))
