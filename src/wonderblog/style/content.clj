(ns wonderblog.style.content
	(:require 
    [wonderblog.constants :as constants])
  )

(def style
  [".content-container"
		{
			"background-color" constants/bg-color-2
			; "width" "100%"
			"max-width" "100%"
			"overflow" "hidden"
			"height" "60vh"
			"border-radius" "2vw"
			"position" "relative"
			"margin-top" "2vh"
			"padding-left" "2vw"
			"padding-top" "2vh"
		}
		["div"
		  {
        ; "display" "inline-block"
        ; "float" "left"
      }
    ]
	  [".content-header"
			{
				"font-size" "3vw"
				"color" constants/text-color-1
				"margin-bottom" "1vw"
			}
		  ["hr"
				{
					"color" constants/text-color-1
					"stroke-width" "4px"
					"width" "90%"
					"float" "left"
					"display" "block"
					"margin" 0
				}
		  ]
	  ]
	  [".content-title"
			{
				"font-size" "2vw"
			}
	  ]
	  [".content-text"
			{
				"font-size" "1vw"
				"white-space" "pre"
				"word-wrap" "break-word"
			}
	  ]
  ]
  )
