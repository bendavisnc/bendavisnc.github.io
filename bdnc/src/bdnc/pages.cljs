(ns bdnc.pages)

(def navigation {:title "navigation"})

(def contact {:title "contact"})

(def experience {:title "experience"})

(def experience-continued {:title "experience-continued"
                           :continued :experience})

(def about {:title "about"})

(def about-continued {:title "about-continued"
                      :continued :about})

(def c {:title "c"})

(def home :about)

;; This is the actual order of how the pages are presented
(def all {:navigation navigation
          :about about
          :about-continued about-continued
          :contact contact
          :experience experience
          :experience-continued experience-continued
          :c c})
