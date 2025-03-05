(ns bdnc.pages)

(def nav {:title "navigation"})

(def contact {:title "contact"})

(def exp {:title "experience"})

(def experience-continued {:title "experience-continued"
                           :continued :experience})

(def about {:title "about"})

(def about-continued {:title "about-continued"
                      :continued :about})

(def home :about)

;; This is the actual order of how the pages are presented
(def all {:navigation nav
          :about about
          :about-continued about-continued
          :experience exp
          :experience-continued experience-continued
          :contact contact})
