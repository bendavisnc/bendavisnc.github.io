(ns bdnc.pages)

(def navigation {:title "navigation"})

(def contact {:title "contact"})

(def experience {:title "experience"})

(def a {:title "a"})

(def c {:title "c"})

(def home :a)

;; This is the actual order of how the pages are presented
(def all {:navigation navigation
          :a a
          :contact contact
          :experience experience
          :c c})
