(ns bdnc.components.basic-page
  [:require [bdnc.components.header :as header]
            [bdnc.components.hamburger :as hamburger]])

(defn component [gs, child]
  [:div {:id "basic-page"}
    [header/component gs, hamburger/component]
    child])
