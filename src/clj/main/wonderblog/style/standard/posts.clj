(ns wonderblog.style.standard.posts
  (:require [wonderblog.style.standard.constants :as constants])
  )

(def style*
  '[
    [".content-header.posts-header"
      {
      }
      ["span"
        {
          "font-size" constants/small-font-size
        }
      ]
    ]
    [".posts-content"
      {
      }
      ["a"
        {
          "display" "block"
          "margin-bottom" constants/standard-gap
          "font-size" constants/small-font-size
          "text-decoration" "none"
        }
      ]
    ]
  ])

(def style
  (eval style*))

