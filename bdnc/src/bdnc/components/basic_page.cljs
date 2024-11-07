(ns bdnc.components.basic-page
  [:require [bdnc.components.header :as header]])

(defn component [child]
  [:div {:id "basic-page"}
    (println "hiii")
    [header/component]
    child])
