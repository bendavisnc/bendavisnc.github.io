(ns wonderblog.style.banner
	(:require 
    [wonderblog.style.constants :as constants])
  )

(def style
  [".banner"
   {
    "background-color" constants/bg-color-2
    "width" "100%"
    ; "height" "30vh"
    ; "height" "24vh"
    "height" "300px"
    ; "border-radius" "2vw"
    "border-radius" constants/border-radius
    "position" "relative"
    ; "margin-top" "1vh"
    "margin-top" constants/standard-gap
   }
   ["a.sitelink"
    {
      "color" (str constants/primary-color-1 " !important")
      ; "font-size" "14vh"
      "font-size" constants/super-massive-font-size
      "position" "absolute"
      ; "left" "2vw"
      "left" "10px"
      ; "bottom" "1vh"
      "bottom" "10px"
      "display" "block"
    }
   ]
   [".bannerlinks"
    {
      "position" "absolute"
      ; "left" "44vw"
      ; "left" "42vw"
      "left" "700px"
      ; "bottom" "1vw"
      "bottom" "8px"
    }
    ["a.navlink"
      {
        "position" "absolute"
        "bottom" "0"
        ; "width" "15vh"
        ; "height" "15vh"
        "width" "150px"
        "height" "150px"
        ; "padding-right" "2vw"
        ; "display" "inline-block"
        ; "vertical-align" "baseline"
      }
      
    ]
   ]
  ])
