(ns wonderblog.style.mobile.constants
  (:require 
    [wonderblog.style.standard.constants :as standard-constants]
  ))

;;
;; colors

(def bg-color-1 standard-constants/bg-color-1)
(def bg-color-2 standard-constants/bg-color-2)
(def primary-color-1 standard-constants/primary-color-1)
(def text-color-1 standard-constants/text-color-1)
(def main-font-color standard-constants/main-font-color)
; (def main-font-color "red")


;;
;; dimensions

; (def banner-height "30vh")
(def banner-height "200px")

(def content-width "90vw")

; (def border-radius "4vw")
(def border-radius "20px")


; (def super-massive-font-size "11vw")
; (def super-massive-font-size "14vw")
; (def super-massive-font-size standard-constants/super-massive-font-size)
(def super-massive-font-size "50px")

; (def large-font-size "7vw")
; (def large-font-size standard-constants/large-font-size)
(def large-font-size super-massive-font-size)

; (def medium-font-size "5vw")
; (def medium-font-size standard-constants/medium-font-size)
(def medium-font-size "30px")

; (def small-font-size "3vw")
; (def small-font-size standard-constants/small-font-size)
(def small-font-size "20px")

(def standard-gap "5vh")
