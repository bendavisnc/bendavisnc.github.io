(ns wonderblog.style.banner
	(:require 
    [wonderblog.constants :as constants])
  )

(def style
  [".banner"
   {
    "background-color" constants/bg-color-2
    "width" "100%"
    "height" "30vh"
    "border-radius" "2vw"
    "position" "relative"
    "margin-top" "1vh"
   }
   ["a"
    {
      "color" (str constants/primary-color-1 " !important")
      "font-size" "14vh"
      "position" "absolute"
      "left" "2vw"
      "bottom" "1vw"
    }
   ]
  ])
