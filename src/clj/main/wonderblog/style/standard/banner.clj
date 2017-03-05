(ns wonderblog.style.standard.banner
  (:require [wonderblog.style.standard.constants :as constants])
  )

(def style* 
  '[".banner"
    {
      ; "background-color" constants/bg-color-2
      "background-color" "rgba(254, 254, 254, 0.8)"
      "width" "100%"
      "height" constants/banner-height
      "border-radius" constants/border-radius
      "position" "relative"
      "margin-top" constants/standard-gap
    }
    ["a.sitelink"
      {
        "color" (str constants/primary-color-1 " !important")
        "font-size" constants/super-massive-font-size
        "position" "absolute"
        "left" "10px"
        "bottom" "-10px"
        "display" "block"
      }
    ]
    ["a.navlink"
      {
        "position" "absolute"
        "bottom" "10px"
        "width" "100px"
        "height" "100px"
        "background-size" "contain"
        "background-repeat" "no-repeat"
      }
    ]
    ["a.navlink-0"
      {
        ; "left" "690px"
        "left" "720px"
        ; "left" "730px"
        ; "left" "750px"
      }
    ]
    ["a.navlink-1"
      {
        ; "left" "850px"
        ; "left" "860px"
        ; "left" "870px"
        "left" "875px"
        ; "left" "890px"
        ; "left" "900px"
      }
    ]
    ["a.navlink-2"
      {
        ; "left" "970px"
        ; "left" "1000px"
        "left" "1030px"
        ; "left" "1010px"
        ; "left" "1030px"
        ; "left" "1030px"
        ; "left" "1070px"
      }
    ]
  ])

(def style (eval style*))
