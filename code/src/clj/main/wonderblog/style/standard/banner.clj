(ns wonderblog.style.standard.banner
  (:require [wonderblog.style.standard.constants :as constants])
  )

(def style* 
  '[".banner"
    {
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
        "left" "720px"
      }
    ]
    ["a.navlink-1"
      {
        "left" "875px"
      }
    ]
    ["a.navlink-2"
      {
        "left" "1030px"
      }
    ]
  ])

(def style (eval style*))
