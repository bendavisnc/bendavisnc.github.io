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
      ; "overflow" "hidden"
      "height" "60vh"
      "border-radius" "2vw"
      "position" "relative"
      "margin-top" "2vh"
      "padding-left" "2vw"
      "padding-top" "2vh"
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
        "font-size" "6vh"
        "margin-bottom" "1vw"
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
        "font-size" "5vh"
      }
    ]
    [".content-text"
      {
        ; "font-size" "1vw"
        "font-size" "3vh"
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
        "font-size" "3vh"
        "margin-left" "2vw"
        ; "margin-top" "2vh"
        "margin-bottom" "2vh"
        "display" "inline-block"
      }
      ["a"
        {
          "text-decoration" "none"
          "margin-right" "1vw"
        }
        ["div"
          {
            "float" "left"
            "margin-right" "1vw"
          }
          ["img"
            {
              ; "width" "10vw"
              ; "height" "auto"
              "width" "auto"
              "height" "10vw"
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
