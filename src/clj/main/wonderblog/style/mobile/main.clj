(ns wonderblog.style.mobile.main
  (:require 
    [wonderblog.style.mobile.constants :as constants]
    [wonderblog.style.standard.main :as standard-main]
    )
  )

(def style*
  [
    ["body" 
      {
        "background-size" "1200px 800px"
        "background-position" "60% 30%"
        "min-height" "100vw"
      }
    ]
    ; ["body, html" 
      ; {:overflow "auto"}
    ; ]
  ]
  )

(def style
  (vec
    (concat
      (eval standard-main/style*)
      style*
      )))
