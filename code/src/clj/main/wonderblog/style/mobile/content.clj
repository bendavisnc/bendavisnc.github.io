(ns wonderblog.style.mobile.content
  (:require 
    [wonderblog.style.mobile.constants :as constants]
    [wonderblog.style.standard.content :as standard-content]
    )
  )

(def style*
  [".content-container"
    {
      "height" "100vw" 
      "padding-left" "20px"
    }
    [".content-links"
      {
        "margin-left" "4vw"
      }
    ]
  ])

(def style
  (vec
    (concat
      (eval standard-content/style*)
      style*
      )))
