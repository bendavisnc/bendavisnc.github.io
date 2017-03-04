(ns wonderblog.style.standard.main
  (:require
    [wonderblog.style.standard.constants :as constants]
    [wonderblog.style.standard.banner :as banner]
    [wonderblog.style.standard.content :as content]
    [wonderblog.style.standard.posts :as posts]
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
    posts/style
  ])