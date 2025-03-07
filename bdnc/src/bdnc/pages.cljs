(ns bdnc.pages)

(def navigation-page {:title "navigation"})

(def contact-page {:title "contact"})

(def experience-page {:title "experience"})

(def experience-continued-page {:title "experience-continued"
                                :continued :experience})

(def about-page {:title "about"})

(def about-continued-page {:title "about-continued"
                           :continued :about})

(def home-page :about)

;; This is the actual order of how the pages are presented
(def all {:navigation navigation-page
          :about about-page
          :about-continued about-continued-page
          :experience experience-page
          :experience-continued experience-continued-page
          :contact contact-page})
