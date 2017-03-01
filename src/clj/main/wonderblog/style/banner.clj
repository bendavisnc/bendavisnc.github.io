(ns wonderblog.style.banner
	(:require 
    [wonderblog.style.constants :as constants])
  )

(def banner-height "300px")

(def style
  [".banner"
    {
      "background-color" constants/bg-color-2
      "width" "100%"
      "height" banner-height
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
        "bottom" "10px"
        "display" "block"
      }
    ]
    ["a.navlink"
      {
        "position" "absolute"
        "bottom" "10px"
        "background-color" "pink"
        ; "width" "150px"
        ; "height" "150px"
        "width" "100px"
        "height" "100px"
      }
    ]
  ])
