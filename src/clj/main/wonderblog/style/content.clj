(ns wonderblog.style.content
  (:require 
    [wonderblog.style.constants :as constants])
  )

(def style
  [".content-container"
    {
      "background-color" constants/bg-color-2
      "width" "100%"
      ; "width" constants/content-width
      ; "max-width" "100%"
      ; "overflow" "hidden"
      "height" "60vh" ; not sure about this
      ; "height" "10%"
      "box-sizing" "border-box"
      ; "border-radius" "2vw"
      "border-radius" constants/border-radius
      "position" "relative"
      ; "margin-top" "2vh"
      "margin-top" constants/standard-gap
      ; "padding-left" "2vw"
      "padding-left" "40px"
      ; "padding-top" "2vh"
      "padding-top" "40px"
      ; "overflow-y" "scroll"
      "overflow-y" "auto"
      "color" constants/text-color-1
    }
    ["div"
      {
        ; "display" "inline-block"
        ; "float" "left"
      }
    ]
    [".content-header"
      {
        ; "font-size" "6vh"
        "font-size" constants/large-font-size
        ; "margin-bottom" "1vw"
        "margin-bottom" "20px"
      }
      ["hr" ; http://stackoverflow.com/questions/6382023/changing-the-color-of-a-hr-element
        {
          ; "color" 
          "background-color" "lightgrey"
          "color" "lightgrey"
          "border" "none"
          "height" "2px"
          "width" "90%"
          "float" "left"
          "display" "block"
          "margin" 0
        }
      ]
      ["span"
        {
          ; "width" "10vw"
          "display" "block"
        }
      ]
    ]
    [".content-title"
      {
        ; "font-size" "2vw"
        ; "font-size" "5vh"
        "font-size" constants/medium-font-size
      }
    ]
    [".content-text"
      {
        ; "font-size" "1vw"
        ; "font-size" "3vh"
        "font-size" constants/small-font-size
        ; "white-space" "pre-line"
        ; "word-wrap" "break-word"
        ; "overflow" "auto"
        ; "overflow-y" "auto"
        ; "overflow-y" "scroll"
        ; "background-color" "blue"
        ; "z-index" 99
        ; "height" "50vh"
        ; "width" "100%"
        ; "width" "600px"
        ; "display" "inline-block"
      }
      ["p"
        {
          "white-space" "pre-line"
          ; "word-wrap" "break-word"
          ; "white-space" "normal"
          ; "width" "600px"
          ; "display" "inline-block"
          ; "background-color" "pink"
        }
      ]
    ]
    [".content-links"
      {
        ; "font-size" "3vh"
        "font-size" constants/medium-font-size
        ; "margin-left" "2vw"
        "margin-left" "100px"
        ; "margin-top" "2vh"
        ; "margin-bottom" "2vh"
        "margin-bottom" "20px"
        "display" "inline-block"
      }
      ["a"
        {
          "text-decoration" "none"
          ; "margin-right" "1vw"
          "margin-right" "20px"
        }
        ["div"
          {
            "float" "left"
            ; "margin-right" "1vw"
            "margin-right" "20px"
          }
          ["img"
            {
              ; "width" "10vw"
              ; "height" "auto"
              "width" "auto"
              ; "height" "10vw"
              "height" "40px"
            }
          ]
        ]
      ]
    ]
    [".talk-links"
      {
        ; "display" "block"
      }
      ["a"
        {
          "display" "block"
        }

      ]
    ]
  ]
  )
