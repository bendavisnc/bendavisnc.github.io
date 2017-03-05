(ns wonderblog.style.standard.main
  (:require
    [wonderblog.style.standard.constants :as constants]
    [wonderblog.style.standard.banner :as banner]
    [wonderblog.style.standard.content :as content]
    [wonderblog.style.standard.posts :as posts]
  ))

(def style*
  '[
    ["@font-face"
      {
        "font-family" "Raleway"
        "src" "url('../fonts/Raleway/Raleway-ExtraLight.ttf')"
      }
    ]
    ["body" 
      {
        "background-image" "url(\"../img/teapartybg.png\")"
        "background-size" "100% auto"
        "font-family" "'Raleway', sans-serif"
      }
    ]
    ["body, html" 
      {:overflow "hidden"}
    ]
    [".container"
      {
        ; "width" "70vw"
        "width" constants/content-width
        ; "height" "100vh"
        "height" "100%"
        "margin-left" "auto"
        "margin-right" "auto"
      }
    ]
  ]
  )

(def style (eval style*))
