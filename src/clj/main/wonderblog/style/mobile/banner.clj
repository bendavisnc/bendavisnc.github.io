(ns wonderblog.style.mobile.banner
  (:require 
    [wonderblog.style.mobile.constants :as constants]
    [wonderblog.style.standard.banner :as standard-banner]
    )
  )

(def style* 
  [".banner"
    {
      ; "opacity" 0.8
      "background-color" "rgba(254, 254, 254, 0.8)"
    }
    ["a.sitelink"
      {
        ; "left" "2vw"
        "left" "14px"
        ; "bottom" "100px"
        "bottom" "130px"
      }
    ]
    ["a.navlink"
      {
        "width" "90px"
        "height" "90px"
      }
    ]
    ["a.navlink-0"
      {
        ; "left" "60vw"
        ; "left" "6vw"
        ; "left" "20px"
        "left" "20px"
        ; "left" "200px"
      }
    ]
    ["a.navlink-1"
      {
        ; "left" "70vw"
        ; "left" "30vw"
        ; "left" "33vw"
        ; "left" "140px"
        ; "left" "130px"
        "left" "120px"
        ; "left" "300px"
      }
    ]
    ["a.navlink-2"
      {
        ; "left" "80vw"
        ; "left" "54vw"
        ; "left" "60vw"
        ; "left" "250px"
        "left" "220px"
        ; "left" "260px"
        ; "left" "400px"
      }
    ]
  ])

(def style
  (vec
    (concat
      (eval standard-banner/style*)
      style*
      )))
