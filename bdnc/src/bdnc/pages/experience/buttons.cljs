(ns bdnc.pages.experience.buttons
  (:require
   [bdnc.pages.experience.svgs :as svgs]))

(defn previous-button [props]
  [:button props
   svgs/previous-icon])

(defn next-button [props]
  [:button (update-in props [:class] conj "transform rotate-180")
   svgs/previous-icon])

(defn expand-button [props is-active?]
  [:button props
   (if is-active? svgs/unexpand-icon svgs/expand-icon)])
