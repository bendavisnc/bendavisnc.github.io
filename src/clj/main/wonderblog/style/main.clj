(ns wonderblog.style.main
  (:require
    [wonderblog.style.constants :as constants]
    [wonderblog.style.banner :as banner]
    [wonderblog.style.content :as content]
  ))

;;
;;
;; Styles for the entire site.

(def custom-fonts
  ["@font-face"
   {
     "font-family" "Raleway"
     "src" "url('../fonts/Raleway/Raleway-ExtraLight.ttf')"
   }])


(def body
  ["body" 
   {
     ; "background-color" constants/bg-color-1
     "background-image" "url(\"../img/teapartybg.png\")"
     "background-size" "100% auto"
     "font-family" "'Raleway', sans-serif"
   }])

(def html-and-body
  ["body, html" {:overflow "hidden"}])

(def container
  [".container"
    {
      ; "width" "70vw"
      "width" constants/content-width
      ; "height" "100vh"
      "height" "100%"
      "margin-left" "auto"
      "margin-right" "auto"
    }
  ])

     
  

(def all-style
  [
    custom-fonts
    body
    html-and-body
    container
    banner/style
    content/style
  ])