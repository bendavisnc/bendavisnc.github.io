(ns wonderblog.style.banner
	(:require 
    [wonderblog.constants :as constants])
  )

(def style
  [".banner"
   {
    "background-color" constants/bg-color-2
    "width" "100%"
    ; "height" "30vh"
    "height" "24vh"
    "border-radius" "2vw"
    "position" "relative"
    "margin-top" "1vh"
   }
   ["a.sitelink"
    {
      "color" (str constants/primary-color-1 " !important")
      "font-size" "14vh"
      "position" "absolute"
      "left" "2vw"
      "bottom" "1vh"
      "display" "block"
    }
   ]
   [".bannerlinks"
    {
      "position" "absolute"
      "left" "44vw"
      "bottom" "1vw"
    }
    ["a.navlink"
      {
        "padding-right" "2vw"
        "display" "inline-block"
        "vertical-align" "baseline"
      }
    ]
   ]
  ])
